package framework.core.exceptions;

import java.util.Map;

import framework.core.constants.ApplicationStatus;

public class FormValidationException extends ApplicationException {

    private static final long serialVersionUID = 5236046400629103731L;

    private final Map<String, String> error;

    public FormValidationException(String message, Map<String, String> error) {
        super(ApplicationStatus.VALIDATION_FAILED, message);
        this.error = error;
    }

    public FormValidationException(String message, Throwable cause, Map<String, String> error) {
        super(ApplicationStatus.VALIDATION_FAILED, message, cause);
        this.error = error;
    }

    public FormValidationException(Throwable cause, Map<String, String> error) {
        super(ApplicationStatus.VALIDATION_FAILED, cause);
        this.error = error;
    }

    public Map<String, String> getError() {
        return this.error;
    }

}
