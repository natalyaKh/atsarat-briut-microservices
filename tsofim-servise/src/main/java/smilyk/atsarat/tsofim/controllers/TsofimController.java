package smilyk.atsarat.tsofim.controllers;

import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.tsofim.dto.AddTsofimDetailsDto;
import smilyk.atsarat.tsofim.dto.Response;
import smilyk.atsarat.tsofim.enums.RequestOperationName;
import smilyk.atsarat.tsofim.enums.RequestOperationStatus;
import smilyk.atsarat.tsofim.services.tsofim.TsofimDetailsService;
import smilyk.atsarat.tsofim.services.validator.ValidatorService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping()
    public Response createChildDetails(@Valid @RequestBody AddTsofimDetailsDto tsofimDetails) {
        validatorService.checkChildUuidUnique(tsofimDetails.getUuidChild());
        return tsofimDetailsService.addTsofimDetails(tsofimDetails);
    }
}
