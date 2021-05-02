package smilyk.atsarat.children.service.children;

import org.springframework.stereotype.Service;
import smilyk.atsarat.children.dto.AddChildDto;
import smilyk.atsarat.children.dto.Response;
import smilyk.atsarat.children.dto.ResponseChildDto;
import smilyk.atsarat.children.dto.UpdateChildDto;


import java.util.List;
/**
 * {@link Service} for handling {@link smilyk.atsarat.children.model.ChildrenEntity} entity.
 */
@Service
public interface ChildService {
    /**
     * save provided {@link smilyk.atsarat.children.model.ChildrenEntity} entity
     * @param childDetails {@link AddChildDto}
     * @return {@link Response} object with {@link ResponseChildDto}
     */
    Response addChild(AddChildDto childDetails);

    /**
     * delete provided {@link smilyk.atsarat.children.model.ChildrenEntity} entity by uuidChild
     * @param uuidChild of provided {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @return boolean
     */
    Boolean deleteChild(String uuidChild);

    /**
     * Find {@link smilyk.atsarat.children.model.ChildrenEntity} by uuidChild.
     * @param uuidChild of provided {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @return  {@link ResponseChildDto} with provided child UUID or null otherwise.
     */
    ResponseChildDto getChildByUuid(String uuidChild);

    /**
     *  Retrieve all {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @param page, default = 0
     * @param limit, default = 2
     * @return the collection of the {@link ResponseChildDto} objects
     */
    List<ResponseChildDto> getAllChildren(int page, int limit);

    /**
     * Update provided {@link smilyk.atsarat.children.model.ChildrenEntity} by uuidChild
     * @param uuidChild of provided {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @param childDetails of provided {@link smilyk.atsarat.children.model.ChildrenEntity}
     * @return updated {@link UpdateChildDto} object
     */
    UpdateChildDto updateChild(String uuidChild, UpdateChildDto childDetails);

}
