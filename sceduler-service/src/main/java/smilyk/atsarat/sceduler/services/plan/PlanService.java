package smilyk.atsarat.sceduler.services.plan;

import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.dto.AddPlanDto;
import smilyk.atsarat.sceduler.dto.Response;
import smilyk.atsarat.sceduler.dto.ResponsePlanDTO;

import java.util.List;

public interface PlanService {
    public Response addPlanDetails(AddPlanDto planDetails);

    List<ResponsePlanDTO> getPlanDetailsByChildUuid(String uuidChild, int page, int limit);

    ResponsePlanDTO getPlanDetaildByUuid(String uuidPlanDetails);
}
