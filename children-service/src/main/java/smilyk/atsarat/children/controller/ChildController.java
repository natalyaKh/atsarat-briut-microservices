package smilyk.atsarat.children.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import smilyk.atsarat.children.dto.*;


import smilyk.atsarat.children.enums.RequestOperationName;
import smilyk.atsarat.children.enums.RequestOperationStatus;
import smilyk.atsarat.children.service.children.ChildService;
import smilyk.atsarat.children.service.validator.ValidatorService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;


@RestController

@RequestMapping("/child/v1")
public class ChildController {
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    @Autowired
    ValidatorService validatorService;
    @Autowired
    ChildService childService;

    /**
     * method create {@link smilyk.atsarat.children.model.ChildrenEntity}
     * if child exists in DB return exception
     * @return {@link smilyk.atsarat.children.model.ChildrenEntity} created
     */
    @PostMapping()
    public Response createChild(@Valid @RequestBody AddChildDto childDetails) {
        validatorService.checkUniqueTZ(childDetails.getTz());
        return childService.addChild(childDetails);
    }


    /**
     * method update {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @param uuidChild, childDetails
     * @return {@link smilyk.atsarat.children.model.ChildrenEntity} updated
     */
    @PutMapping(path = "/{uuidChild}")
    public Response updateChild(@PathVariable String uuidChild,  @Valid @RequestBody UpdateChildDto childDetails) {
        UpdateChildDto updateChild = childService.updateChild(uuidChild, childDetails);
        return new Response(updateChild,HttpServletResponse.SC_OK, currentDate);
    }

    /**
     * method get {@link smilyk.atsarat.children.model.ChildrenEntity} by Uuid of child
     * @param uuidChild of privoded {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @return {@link ResponseChildDto}
     */
    @GetMapping(path = "/{uuidChild}")
    public Response getChild(@PathVariable String uuidChild) {
        ResponseChildDto childDto = childService.getChildByUuid(uuidChild);
        return new Response(childDto, HttpServletResponse.SC_FOUND, currentDate);
    }

    /**
     * make flag 'deleted' = true
     * @param uuidChild of provided {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @return {@link RequestOperationStatus}
     */
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


    /**
     * method get list of {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @param page, default = 0
     * @param limit, defaule = 2
     * @return list of {@link ResponseChildDto}
     */

    @GetMapping()
    public Response getAllChildren(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ResponseChildDto> childs = childService.getAllChildren(page, limit);
        Type listType = new TypeToken<List<ResponseChildDto>>() {
        }.getType();
        List<ResponseChildDto> returnValue = new ModelMapper().map(childs, listType);
        return new Response(returnValue, HttpServletResponse.SC_FOUND, currentDate);

    }
}
