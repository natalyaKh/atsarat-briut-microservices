package smilyk.atsarat.tsofim.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUtils {

    public UUID generateUuid() {
        return UUID.randomUUID();
    }

}

