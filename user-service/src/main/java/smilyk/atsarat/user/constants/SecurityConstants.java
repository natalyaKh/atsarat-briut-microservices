package smilyk.atsarat.user.constants;


import smilyk.atsarat.utils.AppProperties;
import smilyk.atsarat.utils.SpringApplicationContext;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/users/v1/";

    public static final String SIGN_UP_ADMIN_URL = "/users/v1/admin";
    public static final String VERIFICATION_EMAIL_URL = "/users/v1/email-verification/**";
    public static final String PASSWORD_RESET_REQUEST_URL = "/users/v1/password-reset-request";
    public static final String PASSWORD_RESET_URL = "/users/v1/password-reset";
//    public static final String CREATE_ADMIN = "/users/create-admin";

    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
