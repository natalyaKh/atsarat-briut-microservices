package smilyk.atsarat.children.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.Response;
import smilyk.atsarat.children.service.children.ChildService;
import smilyk.atsarat.children.service.validator.ValidatorService;

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
}
