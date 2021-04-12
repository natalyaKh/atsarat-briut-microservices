package smilyk.atsarat.sceduler.services.rabbit;


import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.models.PlanEntity;

@Service
public interface RabbitService {
    void sendMessageToServer(PlanEntity record);
}
