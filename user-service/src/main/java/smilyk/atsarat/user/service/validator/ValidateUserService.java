package smilyk.atsarat.user.service.validator;

import org.springframework.stereotype.Service;

@Service
public interface ValidateUserService {

    Boolean checkUniqueEmail(String mainEmail);
}
