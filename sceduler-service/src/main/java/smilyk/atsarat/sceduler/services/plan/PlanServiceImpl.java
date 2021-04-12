package smilyk.atsarat.sceduler.services.plan;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.dto.AddPlanDto;
import smilyk.atsarat.sceduler.dto.Response;
import smilyk.atsarat.sceduler.dto.ResponsePlanDTO;
import smilyk.atsarat.sceduler.enums.LoggerMessages;
import smilyk.atsarat.sceduler.models.PlanEntity;
import smilyk.atsarat.sceduler.repo.PlanRepo;
import smilyk.atsarat.sceduler.utils.UserUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
@Service
public class PlanServiceImpl implements PlanService {
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    @Autowired
    UserUtils utils;
    @Autowired
    PlanRepo planRepo;

    public Response addPlanDetails(AddPlanDto planDetails) {
        PlanEntity planEntity = modelMapper.map(planDetails, PlanEntity.class);
        planEntity.setUuidPlan(utils.generateUUID().toString());
        planRepo.save(planEntity);
        LOGGER.info(LoggerMessages.PLAN + LoggerMessages.WITH_UUID + planEntity.getUuidChild()
            + " " + LoggerMessages.FOR_SERVICE + planEntity.getService() + LoggerMessages.WAS_SAVE);
        ResponsePlanDTO responsePlanDTO = modelMapper.map(planEntity, ResponsePlanDTO.class);
        return new Response(responsePlanDTO, HttpServletResponse.SC_CREATED, currentDate);
    }
}
