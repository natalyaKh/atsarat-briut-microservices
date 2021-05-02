package smilyk.atsarat.user.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.user.enums.ErrorMessages;
import smilyk.atsarat.user.models.Users;
import smilyk.atsarat.user.repo.UserRepo;

import java.util.Optional;
/**
 * Implementation of {@link ValidateUserService} interface.
 */
@Service
public class ValidateUserServiceImpl implements ValidateUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUserServiceImpl.class);
    @Autowired
    UserRepo userRepo;

    @Override
    public Boolean checkUniqueEmail(String mainEmail) {
        Optional<Users> userEntity = userRepo.findByMainEmail(mainEmail);
        if (userEntity.isPresent()) {
            LOGGER.info(ErrorMessages.USER_WITH_EMAIL_EXISTS + " " + mainEmail);
            return false;
        }
        return true;
    }
}
