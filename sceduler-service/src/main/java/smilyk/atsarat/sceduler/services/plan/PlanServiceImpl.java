package smilyk.atsarat.sceduler.services.plan;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ResponsePlanDTO> getPlanDetailsByChildUuid(String uuidChild, int page, int limit) {
        if (page > 0) page = page - 1;
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<PlanEntity> plansPage = planRepo.findAll(pageableRequest);
        List<PlanEntity> planEntities = plansPage.getContent();
        if(planEntities.size() == 0){
            LOGGER.info(LoggerMessages.PLAN + LoggerMessages.WITH_UUID + uuidChild +
                LoggerMessages.NOT_FOUND);
            return null;
        }
        List<ResponsePlanDTO> returnValue = new ArrayList<>();
        planEntities.stream().filter(plan -> !plan.getDeleted()).filter(plan -> plan.getUuidChild().equals(uuidChild))
            .map(this::toDto)
            .forEachOrdered(returnValue::add);
        LOGGER.info(LoggerMessages.LIST_OF_PLANNING_DETAILS + LoggerMessages.RETURNED);
        return returnValue;

    }

    @Override
    public ResponsePlanDTO getPlanDetaildByUuid(String uuidPlanDetails) {
        Optional<PlanEntity> optionalPlanEntity = planRepo.findByUuidPlanAndDeleted(uuidPlanDetails,
            false);
        if (!optionalPlanEntity.isPresent()) {
            LOGGER.info(LoggerMessages.PLAN + LoggerMessages.WITH_UUID + uuidPlanDetails +
                LoggerMessages.NOT_FOUND);
            return null;
        }
        return modelMapper.map(optionalPlanEntity.get(), ResponsePlanDTO.class);
    }

    @Override
    public Boolean deletePlanDetails(String uuidCPlanDetails) {
        Optional<PlanEntity> optionalPlanEntity = planRepo.findByUuidPlanAndDeleted(uuidCPlanDetails,
            false);
        if (!optionalPlanEntity.isPresent()) {
            LOGGER.info(LoggerMessages.PLAN + LoggerMessages.WITH_UUID + uuidCPlanDetails +
                LoggerMessages.NOT_FOUND);
            return false;
        }
        PlanEntity planEntity = optionalPlanEntity.get();
        planEntity.setDeleted(true);
        planRepo.save(planEntity);
        LOGGER.info(LoggerMessages.PLAN + LoggerMessages.WITH_UUID + uuidCPlanDetails +
            LoggerMessages.WAS_DELETED);
        return true;
    }

    @Override
    public List<ResponsePlanDTO> getAllPlanDetails(int page, int limit) {
        if (page > 0) page = page - 1;
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<PlanEntity> plansPage = planRepo.findAll(pageableRequest);
        List<PlanEntity> planDetails = plansPage.getContent();
        List<ResponsePlanDTO> returnValue = new ArrayList<>();
        planDetails.stream().filter(plan -> !plan.getDeleted()).map(this::toDto)
            .forEachOrdered(returnValue::add);
        LOGGER.info(LoggerMessages.LIST_OF_PLANNING_DETAILS + LoggerMessages.RETURNED);
        return returnValue;
    }


    private ResponsePlanDTO toDto(PlanEntity planEntity) {
        return modelMapper.map(planEntity, ResponsePlanDTO.class);
    }
}
