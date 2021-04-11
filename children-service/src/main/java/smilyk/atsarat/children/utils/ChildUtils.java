package smilyk.atsarat.children.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChildUtils {

    public UUID generateChildUuidId() {
        return UUID.randomUUID();
    }

}

