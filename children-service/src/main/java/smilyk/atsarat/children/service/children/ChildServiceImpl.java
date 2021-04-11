package smilyk.atsarat.children.service.children;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.Response;
import smilyk.atsarat.children.dto.ResponseChildDto;
import smilyk.atsarat.children.enums.LoggerMessages;
import smilyk.atsarat.children.model.ChildrenEntity;
import smilyk.atsarat.children.repo.ChildRepo;
import smilyk.atsarat.children.utils.ChildUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class ChildServiceImpl implements ChildService {
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ChildServiceImpl.class);
    @Autowired
    ChildRepo childRepo;
    @Autowired
    ChildUtils utils;

    @Override
    public Response addChild(AddChildDto childDetails) {
        ChildrenEntity childrenEntity = modelMapper.map(childDetails, ChildrenEntity.class);
        childrenEntity.setUuidChild(utils.generateChildUuidId().toString());
        ResponseChildDto responseChildDto = modelMapper.map(childrenEntity, ResponseChildDto.class);
        childRepo.save(childrenEntity);
        LOGGER.info(LoggerMessages.CHILD + LoggerMessages.FIRST_NAME + childrenEntity.getFirstName() +
            LoggerMessages.SECOND_NAME + childrenEntity.getSecondName() + LoggerMessages.SAVED);
        return  new Response(responseChildDto, HttpServletResponse.SC_CREATED, currentDate);
    }
}
