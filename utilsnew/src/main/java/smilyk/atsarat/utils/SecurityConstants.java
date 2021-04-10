package smilyk.atsarat.utils;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
