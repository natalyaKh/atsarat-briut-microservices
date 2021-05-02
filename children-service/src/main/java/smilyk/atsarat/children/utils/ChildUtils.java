package smilyk.atsarat.children.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Generation of unique uuid for {@link smilyk.atsarat.children.model.ChildrenEntity}
 */
@Service
public class ChildUtils {

    public UUID generateChildUuidId() {
        return UUID.randomUUID();
    }

}

