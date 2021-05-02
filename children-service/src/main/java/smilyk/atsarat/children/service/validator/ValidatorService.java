package smilyk.atsarat.children.service.validator;

import org.springframework.stereotype.Service;
/**
 *  {@link Service} for validating {@link smilyk.atsarat.children.model.ChildrenEntity} entity.
 */
@Service
public interface ValidatorService {
    Boolean checkUniqueTZ(String tz);
}
