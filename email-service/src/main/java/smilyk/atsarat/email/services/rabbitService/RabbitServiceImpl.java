package smilyk.atsarat.email.services.rabbitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smilyk.atsarat.email.dto.EmailDto;
import smilyk.atsarat.email.dto.EmailVerificationDto;
import smilyk.atsarat.email.enums.Services;
import smilyk.atsarat.email.services.emailServices.EmailService;

@Service
public class RabbitServiceImpl implements RabbitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitServiceImpl.class);


    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${conf.email.queue}")
    public void receivedConfirmationMessage(EmailVerificationDto incomingMessage) {
        emailService.sendRegistrationEmail(incomingMessage);
    }

    @RabbitListener(queues = "${email.queue}")
    public void receivedMessage(EmailDto incomingMessage) {
        Services service = incomingMessage.getService();
        switch (service) {

            case TSOFIM:
                emailService.sendTsofimEmail(incomingMessage);
                break;
            default:
                LOGGER.error("Service: " + incomingMessage.getService() +
                        " not found");
                break;
        }
    }
}
