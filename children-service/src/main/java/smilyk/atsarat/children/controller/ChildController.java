package smilyk.atsarat.children.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.children.dto.*;
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

    @GetMapping(path = "/{uuidChild}")
    public Response getChild(@PathVariable String uuidChild) {
        ResponseChildDto childDto = childService.getChildByUuid(uuidChild);
        return new Response(childDto, HttpServletResponse.SC_FOUND, currentDate);
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


    @PutMapping(path = "/{uuidChild}")
    public Response updateChild(@PathVariable String uuidChild, @Valid @RequestBody UpdateChildDto childDetails) {
        UpdateChildDto updateChild = childService.updateChild(uuidChild, childDetails);
        return new Response(updateChild,HttpServletResponse.SC_OK, currentDate);
    }
}
