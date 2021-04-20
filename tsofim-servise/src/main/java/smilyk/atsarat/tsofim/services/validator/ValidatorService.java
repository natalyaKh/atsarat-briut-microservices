package smilyk.atsarat.tsofim.services.validator;

import org.springframework.stereotype.Service;

@Service
public interface ValidatorService {
     void checkChildUuidUnique(String uuidChild);
}
