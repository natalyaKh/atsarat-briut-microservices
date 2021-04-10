package smilyk.atsarat.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 * create uuid
 */
@Component
public class UuidUtils {
    /**
     *
     * @return random uuid
     */
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
