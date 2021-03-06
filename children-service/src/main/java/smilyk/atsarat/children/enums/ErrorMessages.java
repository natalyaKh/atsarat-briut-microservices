package smilyk.atsarat.children.enums;

/**
 * Errors messages for Child Service
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
    CHILD_WITH_TZ_EXISTS("child with provided teudat zeit exists "),
   RESP_PERSON_WITH_EMAIL_EXISTS ("response person with provided e-mail exists "),
    CLEAN_DATABASE_ERROR("Can`t clean db, check what happened"),
    USER_WITH_UUID("Child with provided uuid: "),
    RESP_PERS_WITH_UUID("Response person with provided uuid: "),
    NOT_FOUND(" not found "),
    WITH_EMAIL("with e - mail "),
    WITH_TZ (" with provided teudat zeut "),
    EXISTS( "exists"),
    CHILD(" child ");

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
