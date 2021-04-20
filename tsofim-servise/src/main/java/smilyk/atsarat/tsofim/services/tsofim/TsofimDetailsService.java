package smilyk.atsarat.tsofim.services.tsofim;

import org.springframework.stereotype.Service;
import smilyk.atsarat.tsofim.dto.AddTsofimDetailsDto;
import smilyk.atsarat.tsofim.dto.Response;
import smilyk.atsarat.tsofim.dto.ResponseTsofimDetails;
import smilyk.atsarat.tsofim.dto.UpdateTsofimDetailDto;

import java.util.List;

@Service
public interface TsofimDetailsService {
    Response addTsofimDetails(AddTsofimDetailsDto tsofimDetails);


    ResponseTsofimDetails updateTsofimDetails(UpdateTsofimDetailDto tsofimDetails);
}
