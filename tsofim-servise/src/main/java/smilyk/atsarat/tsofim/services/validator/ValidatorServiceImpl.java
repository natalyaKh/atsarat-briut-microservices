package smilyk.atsarat.tsofim.services.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.tsofim.enums.ErrorMessages;
import smilyk.atsarat.tsofim.enums.LoggerMessages;
import smilyk.atsarat.tsofim.exception.TsofimServiceException;
import smilyk.atsarat.tsofim.model.TsofimDetails;
import smilyk.atsarat.tsofim.repo.TsofimDetailsRepo;

import java.util.Optional;

@Service
public class ValidatorServiceImpl implements ValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    @Autowired
    TsofimDetailsRepo repo;
    @Override
    public void checkChildUuidUnique(String uuidChild) {
        Optional<TsofimDetails> tsofimDetails = repo.findByUuidChildAndDeleted(uuidChild, false);
        if (tsofimDetails.isPresent()) {
            LOGGER.error(ErrorMessages.CHILD_WITH_UUID_EXISTS + " " + uuidChild);
            throw new TsofimServiceException(ErrorMessages.CHILD_WITH_UUID_EXISTS.getErrorMessage() + uuidChild);
        }
        LOGGER.info(LoggerMessages.CHECK_UNIQ_CHILD_WITH_UUID + uuidChild);
    }
}
