package smilyk.atsarat.sceduler.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 * Generation of unique uuid for {@link smilyk.atsarat.sceduler.models.PlanEntity}
 */
@Service
public class UserUtils {

    public UUID generateUUID() {
        return UUID.randomUUID();
    }



}

