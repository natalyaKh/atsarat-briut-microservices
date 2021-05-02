package smilyk.atsarat.user.enums;
/**
 * Errors messages for User Service
 */
public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    USER_WITH_EMAIL_EXISTS("user with provided e-mail exists: "),
    RESP_PERSON_WITH_EMAIL_EXISTS ("response person with provided e-mail exists "),
    CLEAN_DATABASE_ERROR("Can`t clean db, check what happened"),
    USER_WITH_UUID("User with provided uuid: "),
    RESP_PERS_WITH_UUID("Response person with provided uuid: "),
    NOT_FOUND(" not found "),
    WITH_EMAIL("with e - mail ");



    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
