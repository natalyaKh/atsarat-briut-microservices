package smilyk.atsarat.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * create uuid
 */
@Component
public  class UuidUtils {
    /**
     *
     * @return random uuid
     */
    public static UUID generateUuid() {
        return UUID.randomUUID();
    }
}
