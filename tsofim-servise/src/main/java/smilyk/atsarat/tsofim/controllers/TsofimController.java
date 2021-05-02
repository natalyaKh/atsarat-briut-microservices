package smilyk.atsarat.tsofim.controllers;

import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.tsofim.dto.AddTsofimDetailsDto;
import smilyk.atsarat.tsofim.dto.Response;
import smilyk.atsarat.tsofim.dto.ResponseTsofimDetails;
import smilyk.atsarat.tsofim.dto.UpdateTsofimDetailDto;
import smilyk.atsarat.tsofim.enums.RequestOperationName;
import smilyk.atsarat.tsofim.enums.RequestOperationStatus;
import smilyk.atsarat.tsofim.services.parser.TsofimCrawlerService;
import smilyk.atsarat.tsofim.services.tsofim.TsofimDetailsService;
import smilyk.atsarat.tsofim.services.validator.ValidatorService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for Tsofim-service
 */
@RestController
@RequestMapping("/tsofiml/v1")
public class TsofimController {

    private static final String NOT_FOUND_STRING = " Can`t find record for child with uuid: ";
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    ModelMapper modelMapper = new ModelMapper();


    @Autowired
    TsofimDetailsService tsofimDetailsService;
    @Autowired
    ValidatorService validatorService;
    @Autowired
    TsofimCrawlerService tsofimCrawlerService;


    @PostMapping()
    public Response createChildDetails(@Valid @RequestBody AddTsofimDetailsDto tsofimDetails) {
        validatorService.checkChildUuidUnique(tsofimDetails.getUuidChild());
        return tsofimDetailsService.addTsofimDetails(tsofimDetails);
    }
    @PutMapping()
    public Response updateChildDetails(@Valid @RequestBody UpdateTsofimDetailDto tsofimDetails) {
        ResponseTsofimDetails updateChildDetails = tsofimDetailsService.updateTsofimDetails(tsofimDetails);
        if(updateChildDetails == null){
            return new Response(NOT_FOUND_STRING + tsofimDetails.getUuidChild(),
                HttpServletResponse.SC_NO_CONTENT, currentDate);
        }
        return new Response(updateChildDetails, HttpServletResponse.SC_FOUND, currentDate);
    }
    @GetMapping(path = "child/{uuidChild}")
    public Response getChildDetailsByChildUuid(@PathVariable String uuidChild) {
        ResponseTsofimDetails responseChildDetails = tsofimDetailsService.getChildDetailsByChildUuid(uuidChild);
        if(responseChildDetails == null){
            return new Response(NOT_FOUND_STRING + uuidChild,
                HttpServletResponse.SC_NO_CONTENT, currentDate);
        }
        return new Response(responseChildDetails, HttpServletResponse.SC_FOUND, currentDate);
    }

    @GetMapping("/parse/{uuidChild}")
    public String parseSchool(@PathVariable String uuidChild)  {
        return tsofimCrawlerService.sendFormToTsofim(uuidChild);
    }
}
