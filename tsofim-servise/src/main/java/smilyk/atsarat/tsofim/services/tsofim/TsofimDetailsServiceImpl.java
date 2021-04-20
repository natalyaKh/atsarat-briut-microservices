package smilyk.atsarat.tsofim.services.tsofim;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import smilyk.atsarat.tsofim.dto.AddTsofimDetailsDto;
import smilyk.atsarat.tsofim.dto.Response;
import smilyk.atsarat.tsofim.dto.ResponseTsofimDetails;
import smilyk.atsarat.tsofim.dto.UpdateTsofimDetailDto;
import smilyk.atsarat.tsofim.enums.LoggerMessages;
import smilyk.atsarat.tsofim.model.TsofimDetails;
import smilyk.atsarat.tsofim.repo.TsofimDetailsRepo;
import smilyk.atsarat.tsofim.utils.UserUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class TsofimDetailsServiceImpl implements TsofimDetailsService {
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(TsofimDetailsServiceImpl.class);
    private String currentDate = LocalDateTime.now().toLocalDate().toString();

    @Autowired
    TsofimDetailsRepo tsofimDetailsRepo;
    @Autowired
    UserUtils utils;
    @Value("secretPassword")
    String secretWord;
    @Override
    public Response addTsofimDetails(AddTsofimDetailsDto tsofimDetails) {
        TsofimDetails tsofimDetailsEntity = modelMapper.map(tsofimDetails, TsofimDetails.class);
        tsofimDetailsEntity.setUuidTsofimDetails(utils.generateUuid().toString());
        tsofimDetailsRepo.save(tsofimDetailsEntity);
        LOGGER.info(LoggerMessages.CHILD + LoggerMessages.WITH_UUID + LoggerMessages.WAS_SAVE);
        ResponseTsofimDetails responseTsofimDetails = modelMapper.map(tsofimDetailsEntity, ResponseTsofimDetails.class);
        return new Response(responseTsofimDetails, HttpServletResponse.SC_CREATED, currentDate);
    }

    @Override
    public ResponseTsofimDetails updateTsofimDetails(UpdateTsofimDetailDto tsofimDetails) {
        Optional<TsofimDetails> optionalTsofimDetails = tsofimDetailsRepo.findByUuidChildAndDeleted(tsofimDetails.getUuidChild(),
            false);
        if(!optionalTsofimDetails.isPresent()){
            LOGGER.info(LoggerMessages.CHILD + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
                LoggerMessages.NOT_FOUND);
            return null;
        }
        TsofimDetails tsofimDetailsEntity = optionalTsofimDetails.get();
        if(!tsofimDetails.getGroupTs().isEmpty()){
            tsofimDetailsEntity.setGroupTs(tsofimDetails.getGroupTs());
            LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
                LoggerMessages.WAS_UPDATE +
                ": new Group " + tsofimDetails.getGroupTs());
        }
        if(!tsofimDetails.getPlace().isEmpty()){
            tsofimDetailsEntity.setPlace(tsofimDetails.getPlace());
            LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
                LoggerMessages.WAS_UPDATE +
                ": new Place " + tsofimDetails.getPlace());
        }
        if(!tsofimDetails.getSchool().isEmpty()){
            tsofimDetailsEntity.setSchool(tsofimDetails.getSchool());
            LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
                LoggerMessages.WAS_UPDATE +
                ": new School " + tsofimDetails.getSchool());
        }
        if(!tsofimDetails.getChildClass().isEmpty()){
            tsofimDetailsEntity.setChildClass(tsofimDetails.getChildClass());
            LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
                LoggerMessages.WAS_UPDATE +
                ": new School " + tsofimDetails.getChildClass());
        }
        tsofimDetailsRepo.save(tsofimDetailsEntity);
        LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + tsofimDetails.getUuidChild() +
            LoggerMessages.WAS_UPDATE );
        return modelMapper.map(tsofimDetailsEntity, ResponseTsofimDetails.class);
    }

    @Override
    public ResponseTsofimDetails getChildDetailsByChildUuid(String uuidChild) {
        Optional<TsofimDetails> optionalTsofimDetails = tsofimDetailsRepo.findByUuidChildAndDeleted(uuidChild,
            false);
        if(!optionalTsofimDetails.isPresent()){
            LOGGER.info(LoggerMessages.TSOFIM_DETAILS + LoggerMessages.WITH_UUID + uuidChild +
                LoggerMessages.NOT_FOUND);
            return null;
        }
        return modelMapper.map(optionalTsofimDetails.get(), ResponseTsofimDetails.class);
    }


}
