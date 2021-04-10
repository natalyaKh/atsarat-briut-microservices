package smilyk.atsarat.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AppProperties {
    @Autowired
    private Environment env;

    public String getTokenSecret()
    {
        return env.getProperty("tokenSecret");
    }
}
