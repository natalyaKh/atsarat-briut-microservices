package smilyk.atsarat.tsofim.services.tsofim;

import org.springframework.stereotype.Service;
import smilyk.atsarat.tsofim.dto.AddTsofimDetailsDto;
import smilyk.atsarat.tsofim.dto.Response;
import smilyk.atsarat.tsofim.dto.ResponseTsofimDetails;
import smilyk.atsarat.tsofim.dto.UpdateTsofimDetailDto;

import java.util.List;

/**
 * {@link Service} for handling {@link smilyk.atsarat.tsofim.model.TsofimDetails} entity.
 */
@Service
public interface TsofimDetailsService {
    /**
     * save provided {@link smilyk.atsarat.tsofim.model.TsofimDetails} entity
     * @param tsofimDetails {@link AddTsofimDetailsDto}
     * @return {@link Response} object with {@link ResponseTsofimDetails}
     */
    Response addTsofimDetails(AddTsofimDetailsDto tsofimDetails);

    /**
     * update {@link smilyk.atsarat.tsofim.model.TsofimDetails}
     * @param tsofimDetails
     * @return ResponseTsofimDetails
     */
    ResponseTsofimDetails updateTsofimDetails(UpdateTsofimDetailDto tsofimDetails);
    /**
     * Find {@link smilyk.atsarat.tsofim.model.TsofimDetails} by provided child.
     * @param uuidChild of provided {@link smilyk.atsarat.tsofim.model.TsofimDetails}
     * @return  {@link ResponseTsofimDetails} with provided record UUID or null otherwise.
     */
    ResponseTsofimDetails getChildDetailsByChildUuid(String uuidChild);
}
