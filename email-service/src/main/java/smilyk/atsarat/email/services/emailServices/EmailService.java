package smilyk.atsarat.email.services.emailServices;


import org.springframework.stereotype.Service;
import smilyk.atsarat.email.dto.EmailDto;
import smilyk.atsarat.email.dto.EmailVerificationDto;
/**
 * {@link Service} for sending emails.
 */
@Service
public interface EmailService {

    /**
     * method sends registration email to user. Email contains link confirmation
     * @param mail - email od user
     * @return string
     */
    String sendRegistrationEmail(EmailVerificationDto mail);

    /**
     * methods send email with screen-shot of tsofim atsarat-briut to user`s email
     * @param mail - email od user
     * @return String
     */
    String sendTsofimEmail(EmailDto mail);

    /**
     * method send email to administrator if something is wrong ans mail service can not send email to user
     * @param email - email of administrator
     * @param service - problems service
     * @param userFirstName name of user
     * @param userLastName second name od user
     */
    void emailError(String email, String service, String userFirstName, String userLastName);

}

