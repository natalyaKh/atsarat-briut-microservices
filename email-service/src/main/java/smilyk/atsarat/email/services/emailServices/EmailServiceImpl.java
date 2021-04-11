package smilyk.atsarat.email.services.emailServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import smilyk.atsarat.email.controller.EmailController;
import smilyk.atsarat.email.dto.EmailDto;
import smilyk.atsarat.email.dto.EmailVerificationDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Base64;

@Service

public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    public static final String APPLICATION_NAME = " Atsarat Briut ";
    private static final String CURRENTLY_DATE = LocalDateTime.now().toLocalDate().toString();
    private static final String GYMNAST_SERVICE = " gymnast service";
    private static final String SCHOOL_SERVICE = " school Ben Gurion service ";
    private static final String TSOFIM_SERVICE = " tsofim group כפיר service";
    private static final String VERIFICATION_SERVICE = " verification email ";
    @Autowired
    JavaMailSender emailSender;
    @Value("${email.address}")
    String adminEmail;
    @Autowired
    Environment env;


    @Override
    public String sendRegistrationEmail(EmailVerificationDto mail) {
        env.getProperty("verification.link");
        MimeMessage msg = emailSender.createMimeMessage();

        String email = mail.getEmail();
        String tokenValue = mail.getTokenValue();
        String lastName = mail.getUserLastName();
        String firstName = mail.getUserName();
        if (lastName == null) {
            lastName = " ";
        }
        String VERIFY_LINK = env.getProperty("verification.link");
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        ////        The HTML body for the email.
        final String htmlMsg =
                "<h1> Hi " + firstName + " " + lastName + ". Please verify your email address</h1>"
                        + "<p>Thank you for registering with our app. To complete registration process and be able to log in,"
                        + " click on the following link: "
                        + " <a href='" + VERIFY_LINK + "?token=" + tokenValue + "'>" + "<br/><br/>" + " Final step to complete your registration" +
                        "</a><br/><br/>"
                        + "Thank you! And we are waiting for you inside!";
        try {
            helper.setTo(email);
            helper.setTo(email);
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            helper.setSubject("Verification email from" + APPLICATION_NAME);
            helper.setText(htmlMsg, true);
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            emailSender.send(msg);
            LOGGER.info(" Verification email was send to e-mail: " + email);
        } catch (MessagingException ex) {
            LOGGER.error("verification email: something is wrong" + ex);
            emailError(adminEmail, VERIFICATION_SERVICE, lastName, firstName);
            LOGGER.error("send error-email to administrator" + mail);
            System.out.println(ex.getMessage());
        }
        return "Email send!";
    }


    @Override
    public String sendTsofimEmail(EmailDto mail) {
        String email = mail.getEmail();
        String picture = mail.getPicture();
        String lastName = mail.getLastName();
        if (lastName == null) {
            lastName = " ";
        }
        String firstName = mail.getFirstName();
        if (firstName == null) {
            firstName = " ";
        }
        String childFirstName = mail.getChildFirstName();
        if (childFirstName == null) {
            childFirstName = " ";
        }
        String childSecondName = mail.getChildSecondName();
        if (childSecondName == null) {
            childSecondName = " ";
        }

        MimeMessage msg = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        final String htmlMsg =
                "<h1> Hi " + firstName + " " + lastName + "</h1>" +
                        "<br><hr> you filled out a confirmation that the child " + childFirstName + " " + childSecondName + " " +
                        "can attend tsofim group  <br><hr>" +
                        "Date: " + CURRENTLY_DATE + "<br><hr>" +
                        "Resolution documents you can check in attached file";
        try {
            helper.setTo(email);
            byte[] decodedImage = Base64.getDecoder().decode(picture);
            helper.addAttachment("tsofim_resolution.jpg", new ByteArrayResource(decodedImage));
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            helper.setSubject("Atsarat Briut for Tsofim email from" + APPLICATION_NAME);
            helper.setText(htmlMsg, true);
            emailSender.send(msg);
            LOGGER.info(" Tsofim atsarat briut was send to e-mail: " + email);
        } catch (MessagingException ex) {
            LOGGER.error(" tsofim atsarat briut email: something is wrong" + ex);
            emailError(adminEmail, TSOFIM_SERVICE, lastName, firstName);
            LOGGER.error("send error-email to administrator" + mail);
            System.out.println(ex.getMessage());
            return "Error -> email not send";
        }
        return "Email send!";
    }

    @Override
    public void emailError(String email, String service, String userFirstName, String userLastName) {
        MimeMessage msg = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        final String htmlMsg =
                "<h1> Hi  administrator </h1>>" +
                        " <hr><br>there is some problems during sending email from " + service
                        + "to e-mail: " + email + " for " + userFirstName + userLastName + ". \n" +
                        "<br>please, check logfile.";
        try {
            helper.setTo(email);
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            helper.setSubject("Error email" + APPLICATION_NAME);
            helper.setText(htmlMsg, true);
            emailSender.send(msg);
            LOGGER.info(" Error email was send to  administrator to e-mail: " + email);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
            LOGGER.error("something wrong with error e-mail");
        }
    }


}
