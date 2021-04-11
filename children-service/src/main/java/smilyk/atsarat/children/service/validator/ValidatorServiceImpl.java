package smilyk.atsarat.children.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.children.enums.ErrorMessages;
import smilyk.atsarat.children.enums.LoggerMessages;
import smilyk.atsarat.children.exception.ChildrenServiceException;
import smilyk.atsarat.children.model.ChildrenEntity;
import smilyk.atsarat.children.repo.ChildRepo;

import java.util.Optional;

@Service
public class ValidatorServiceImpl implements ValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    final
    ChildRepo childRepo;

    public ValidatorServiceImpl(ChildRepo childRepo) {
        this.childRepo = childRepo;
    }

    @Override
    public Boolean checkUniqueTZ(String tz) {
        Optional<ChildrenEntity> optionalChildrenEntity = childRepo.findByTzAndDeleted(
                tz, false);
        if (optionalChildrenEntity.isPresent()) {
           LOGGER.error(LoggerMessages.CHILD + LoggerMessages.PROVIDED_TZ + LoggerMessages.EXISTS);
            throw new ChildrenServiceException(ErrorMessages.CHILD_WITH_TZ_EXISTS.getErrorMessage());
        }
        return true;
    }
}

