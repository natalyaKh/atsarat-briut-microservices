package smilyk.atsarat.tsofim.services.validator;

import org.springframework.stereotype.Service;
/**
 *  {@link Service} for validating {@link ValidatorService} entity.
 */
@Service
public interface ValidatorService {
     void checkChildUuidUnique(String uuidChild);
}
