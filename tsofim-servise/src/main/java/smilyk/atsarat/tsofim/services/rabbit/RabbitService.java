package smilyk.atsarat.tsofim.services.rabbit;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import smilyk.atsarat.tsofim.dto.EmailDto;
import smilyk.atsarat.tsofim.dto.RabbitDto;
/**
 * {@link Service} for sending messagesto RabbitMQ
 */
@Component
public interface RabbitService {
    /**
     * Listener. Waiting messages from scheduler-service
     * @param incomingMessage
     */
    void receivedMessage(RabbitDto incomingMessage);

    /**
     * sending {@link EmailDto} data to email-service
     * @param emailDto sending data to email-service
     */
    void sendToEmailService(EmailDto emailDto);
}
