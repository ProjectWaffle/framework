package framework.core.enums;

public enum ApplicationStatus {

    EXPIRED_CREDENTIALS(21, "Password has already expired."), EXPIRED_PROFILE(22, "Profile has already expired."), FAILED_TO_LOCALIZED(
            100, "Unable to proceed localizing certain text entries."), FORBIDDEN(403, "Access Denied."), INVALID_USER(
            20, "Either the username of the password is invalid."), SUCCESS(0, "Successful."), SYSTEM_EXCEPTION(1,
            "Unhandled exception.");

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
