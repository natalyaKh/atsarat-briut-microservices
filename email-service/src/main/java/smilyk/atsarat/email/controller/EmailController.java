package smilyk.atsarat.email.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smilyk.atsarat.email.dto.EmailVerificationDto;
import smilyk.atsarat.email.services.emailServices.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;


    @PostMapping("/verification-email")
    String sendSimpleEmail(@RequestBody EmailVerificationDto mail)  {
        return emailService.sendRegistrationEmail(mail);
    }

    private void emailError(String email, String service, String userFirstName, String userLastName){
        emailService.emailError(email, service, userFirstName, userLastName);
    }
}
