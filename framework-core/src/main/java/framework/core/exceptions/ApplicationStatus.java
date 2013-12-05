package framework.core.exceptions;

public enum ApplicationStatus {

    SUCCESS(0, "Successful."),
    
    VALIDATION_FAILED(11, "Validation Failed."),
    
    INVALID_USER(21, "Either the username or the password is invalid."),
    EXPIRED_CREDENTIALS(22, "Password has already expired."),
    EXPIRED_PROFILE(23, "Profile has already expired."),
    FAILED_TO_LOCALIZED(24, "Unable to proceed localizing certain text entries."),
    SESSION_ALREADY_EXIST(25, "User is already authenticated in another session."),
    
    NO_RECORDS_FOUND(204, "No record(s) found. "),
    FORBIDDEN(403, "Authorization required to access this page."),
    SERVICE_NOT_FOUND(404, "Service not found."),
    SYSTEM_EXCEPTION(500, "Unhandled exception.");
    
    private Integer code;
    private String message;

    ApplicationStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}