package smilyk.atsarat.children.service.children;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.Response;
import smilyk.atsarat.children.dto.ResponseChildDto;
import smilyk.atsarat.children.dto.UpdateChildDto;
import smilyk.atsarat.children.enums.ErrorMessages;
import smilyk.atsarat.children.enums.LoggerMessages;
import smilyk.atsarat.children.exception.ChildrenServiceException;
import smilyk.atsarat.children.model.ChildrenEntity;
import smilyk.atsarat.children.repo.ChildRepo;
import smilyk.atsarat.children.utils.ChildUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Boolean deleteChild(String uuidChild) {
        Optional<ChildrenEntity> optionalChildrenEntity = childRepo.findByUuidChildAndDeleted(uuidChild, false);
        if (!optionalChildrenEntity.isPresent()) {
            LOGGER.error(LoggerMessages.CHILD_WITH_UUID  + uuidChild + LoggerMessages.NOT_FOUND);
            throw new ChildrenServiceException(
                ErrorMessages.USER_WITH_UUID + uuidChild + ErrorMessages.NOT_FOUND);
        }
        ChildrenEntity childrenEntity = optionalChildrenEntity.get();
        childrenEntity.setDeleted(true);
        childRepo.save(childrenEntity);
        LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidChild + LoggerMessages.DELETED);
        return true;
    }

    @Override
    public ResponseChildDto getChildByUuid(String uuidChild) {
        Optional<ChildrenEntity> optionalChildrenEntity = childRepo.findByUuidChildAndDeleted(uuidChild, false);
        if (!optionalChildrenEntity.isPresent()) {
            LOGGER.error(LoggerMessages.CHILD_WITH_UUID  + uuidChild + LoggerMessages.NOT_FOUND);
            throw new ChildrenServiceException(
                ErrorMessages.USER_WITH_UUID + uuidChild + ErrorMessages.NOT_FOUND);
        }
        LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.WAS_RETURND);
        return modelMapper.map(optionalChildrenEntity.get(), ResponseChildDto.class);
    }

    @Override

    public List<ResponseChildDto> getAllChildren(int page, int limit) {
        if (page > 0) page = page - 1;
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ChildrenEntity> childesPage = childRepo.findAll(pageableRequest);
        List<ChildrenEntity> childes = childesPage.getContent();
        List<ResponseChildDto> returnValue = new ArrayList<>();
        childes.stream().filter(child -> !child.getDeleted()).map(this::toDto)
            .forEachOrdered(returnValue::add);
        return returnValue;
    }

    private ResponseChildDto toDto(ChildrenEntity child) {
        return modelMapper.map(child, ResponseChildDto.class);
    }

    public UpdateChildDto updateChild(String uuidChild, UpdateChildDto childDetails) {
        Optional<ChildrenEntity> optionalChildrenEntity = childRepo.findByUuidChildAndDeleted(uuidChild, false);
        if (!optionalChildrenEntity.isPresent()) {
            LOGGER.error(LoggerMessages.CHILD_WITH_UUID  + uuidChild + LoggerMessages.NOT_FOUND);
            throw new ChildrenServiceException(
                ErrorMessages.USER_WITH_UUID + uuidChild + ErrorMessages.NOT_FOUND);
        }
        ChildrenEntity childrenEntity = optionalChildrenEntity.get();
        if(childDetails.getFirstName() != null){
            childrenEntity.setFirstName(childDetails.getFirstName());
            LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.CHANGE + LoggerMessages.FIRST_NAME
                + LoggerMessages.TO + childDetails.getFirstName());
        }
        if(childDetails.getSecondName() != null){
            childrenEntity.setSecondName(childDetails.getSecondName());
            LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.CHANGE + LoggerMessages.SECOND_NAME
                + LoggerMessages.TO + childDetails.getSecondName());
        }
        if(childDetails.getTz() != null){
            childrenEntity.setTz(childDetails.getTz());
            LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.CHANGE + LoggerMessages.PROVIDED_TZ);
        }
        if(childDetails.getUuidParent() != null){
            childrenEntity.setUuidParent(childDetails.getUuidParent());
            LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.CHANGE + LoggerMessages.PARENT
                + LoggerMessages.TO + LoggerMessages.PARENT + LoggerMessages.WITH_UUID +  childDetails.getUuidParent());
        }
        if(childDetails.getUuidRespPers() != null){
            childrenEntity.setUuidRespPers(childDetails.getUuidRespPers());
            LOGGER.info(LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.CHANGE + LoggerMessages.RESP_PERS
                + LoggerMessages.TO + LoggerMessages.RESP_PERS + LoggerMessages.WITH_UUID +  childDetails.getUuidParent());
        }
        childRepo.save(childrenEntity);
        LOGGER.info( LoggerMessages.CHILD_WITH_UUID + uuidChild + LoggerMessages.WAS_UPDATE);
        return modelMapper.map(childrenEntity, UpdateChildDto.class);
    }



}
