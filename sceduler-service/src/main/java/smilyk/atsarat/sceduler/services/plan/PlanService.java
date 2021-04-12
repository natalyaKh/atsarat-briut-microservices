package smilyk.atsarat.sceduler.services.plan;

import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.dto.AddPlanDto;
import smilyk.atsarat.sceduler.dto.Response;

public interface PlanService {
    public Response addPlanDetails(AddPlanDto planDetails);
}
