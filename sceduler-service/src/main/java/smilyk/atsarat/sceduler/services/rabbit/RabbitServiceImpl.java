package smilyk.atsarat.sceduler.services.rabbit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.dto.RabbitSenderDto;
import smilyk.atsarat.sceduler.enums.Services;
import smilyk.atsarat.sceduler.models.PlanEntity;

import java.time.LocalDateTime;
/**
 * Implementation of {@link RabbitService} interface.
 */
@Service

public class RabbitServiceImpl implements RabbitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitServiceImpl.class);
    private static final String type = "direct";
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    String exchange;
    @Value("${tsofim.key}")
    String tsofimRoutingkey;




    @Override
    public void sendMessageToServer(PlanEntity record) {
        LocalDateTime dateNow = LocalDateTime.now();
        Services service = record.getService();
        RabbitSenderDto sendMessage = RabbitSenderDto.builder()
                .uuidChild(record.getUuidChild())
                .build();
        switch (service) {
            case TSOFIM:
                rabbitTemplate.convertAndSend(exchange, tsofimRoutingkey, sendMessage);
                break;
            default:
                LOGGER.info("Not found records" + dateNow.toLocalDate());
                break;
        }
    }
}

