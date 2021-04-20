package smilyk.atsarat.tsofim.services.rabbit;


import org.springframework.stereotype.Component;
import smilyk.atsarat.tsofim.dto.EmailDto;
import smilyk.atsarat.tsofim.dto.RabbitDto;

@Component
public interface RabbitService {
    void receivedMessage(RabbitDto incomingMessage);
    void sendToEmailService(EmailDto emailDto);
}
