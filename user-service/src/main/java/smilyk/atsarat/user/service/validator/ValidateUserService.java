package smilyk.atsarat.user.service.validator;

import org.springframework.stereotype.Service;
/**
 *  {@link Service} for validating {@link smilyk.atsarat.user.models.Users} entity.
 */
@Service
public interface ValidateUserService {

    /**
     * checking if email of user is unique
     * @param mainEmail
     * @return boolean
     */
    Boolean checkUniqueEmail(String mainEmail);
}
