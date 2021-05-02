package smilyk.atsarat.sceduler.services.plan;

import org.springframework.stereotype.Service;
import smilyk.atsarat.sceduler.dto.AddPlanDto;
import smilyk.atsarat.sceduler.dto.Response;
import smilyk.atsarat.sceduler.dto.ResponsePlanDTO;

import java.util.List;
/**
 * {@link Service} for handling {@link smilyk.atsarat.sceduler.models.PlanEntity} entity.
 */
public interface PlanService {
    /**
     * save provided {@link smilyk.atsarat.sceduler.models.PlanEntity} entity
     * @param planDetails {@link AddPlanDto}
     * @return {@link Response} object with {@link ResponsePlanDTO}
     */
    public Response addPlanDetails(AddPlanDto planDetails);
    /**
     * Find {@link smilyk.atsarat.sceduler.models.PlanEntity} by provided child.
     * @param uuidChild of provided {@link smilyk.atsarat.sceduler.models.PlanEntity}
     * @return  {@link ResponsePlanDTO} with provided child UUID or null otherwise.
     */
    List<ResponsePlanDTO> getPlanDetailsByChildUuid(String uuidChild, int page, int limit);

    /**
     * Find {@link smilyk.atsarat.sceduler.models.PlanEntity} by uuid of provided plan .
     * @param uuidPlanDetails of provided {@link smilyk.atsarat.sceduler.models.PlanEntity}
     * @return  {@link ResponsePlanDTO} with provided child UUID or null otherwise.
     */
    ResponsePlanDTO getPlanDetaildByUuid(String uuidPlanDetails);

    /**
     * delete provided {@link smilyk.atsarat.sceduler.models.PlanEntity} entity by uuidChild
     * @param uuidCPlanDetails of provided {@link smilyk.atsarat.sceduler.models.PlanEntity}
     * @return boolean
     */
    Boolean deletePlanDetails(String uuidCPlanDetails);

    /**
     *  Retrieve all {@link smilyk.atsarat.sceduler.models.PlanEntity}
     * @param page, default = 0
     * @param limit, default = 2
     * @return the collection of the {@link Response} objects
     */
    List<ResponsePlanDTO> getAllPlanDetails(int page, int limit);
}
