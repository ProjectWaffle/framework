package framework.core.constants;

public enum ApplicationStatus {

    EXPIRED_CREDENTIALS(21, "Password has already expired."),
    EXPIRED_PROFILE(22, "Profile has already expired."),
    FAILED_TO_LOCALIZED(100, "Unable to proceed localizing certain text entries."),
    FORBIDDEN(403, "Access Denied."),
    INVALID_USER(20, "Either the username or the password is invalid."),
    SERVICE_NOT_FOUND(404, "Service not found."),
    SUCCESS(0, "Successful."),
    SYSTEM_EXCEPTION(500, "Unhandled exception."),
    VALIDATION_FAILED(1, "Validation Failed.");

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