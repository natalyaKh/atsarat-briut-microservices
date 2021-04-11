package smilyk.atsarat.children.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.OperationStatusModel;
import smilyk.atsarat.children.dto.Response;
import smilyk.atsarat.children.enums.RequestOperationName;
import smilyk.atsarat.children.enums.RequestOperationStatus;
import smilyk.atsarat.children.service.children.ChildService;
import smilyk.atsarat.children.service.validator.ValidatorService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController

@RequestMapping("/child/v1")
public class ChildController {
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    @Autowired
    ValidatorService validatorService;
    @Autowired
    ChildService childService;

    @PostMapping()
    public Response createChild(@Valid @RequestBody AddChildDto childDetails) {
        validatorService.checkUniqueTZ(childDetails.getTz());
        return childService.addChild(childDetails);
    }

    @DeleteMapping(path = "/{uuidChild}")
    public Response deleteChild(@PathVariable String uuidChild) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        Boolean deleted = childService.deleteChild(uuidChild);
        if(deleted){
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }else{
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return new Response(returnValue, HttpServletResponse.SC_OK, currentDate);
    }
}
