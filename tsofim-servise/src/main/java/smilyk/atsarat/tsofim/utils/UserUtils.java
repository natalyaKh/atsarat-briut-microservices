package smilyk.atsarat.tsofim.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * generatin of unique UUID for {@link smilyk.atsarat.tsofim.model.TsofimDetails} entity
 */
@Service
public class UserUtils {

    public UUID generateUuid() {
        return UUID.randomUUID();
    }

}

