package smilyk.atsarat.email.services.emailServices;


import org.springframework.stereotype.Service;
import smilyk.atsarat.email.dto.EmailDto;
import smilyk.atsarat.email.dto.EmailVerificationDto;

@Service
public interface EmailService {

    String sendRegistrationEmail(EmailVerificationDto mail);

    String sendTsofimEmail(EmailDto mail);

    void emailError(String email, String service, String userFirstName, String userLastName);

}

