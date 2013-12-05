package framework.core.exceptions;


public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 6245180922857816922L;
    private final ApplicationStatus status;

    public ApplicationException(ApplicationStatus error, String message) {
        super(message);
        this.status = error;
    }

    public ApplicationException(ApplicationStatus error, String message, Throwable cause) {
        super(message, cause);
        this.status = error;
    }

    public ApplicationException(ApplicationStatus error, Throwable cause) {
        super(cause);
        this.status = error;
    }

    public Integer getCode() {
        return this.status.getCode();
    }

    public String getErrorMessage() {
        return this.status.getMessage();
    }

    public ApplicationStatus getStatus() {
        return this.status;
    }

}
