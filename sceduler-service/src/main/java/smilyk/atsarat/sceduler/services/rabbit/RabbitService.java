package smilyk.atsarat.sceduler.services.rabbit;


import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.models.PlanEntity;
/**
 * {@link Service} for sending messagesto RabbitMQ
 */
@Service
public interface RabbitService {

    /**
     * sendind message to RabbitMQ for provided service
     * @param record
     */
    void sendMessageToServer(PlanEntity record);
}
