package framework.core.exceptions;

import framework.core.constants.ApplicationStatus;

public abstract class AuthenticationException extends ApplicationException {

    private static final long serialVersionUID = 7940333532126299159L;

    public AuthenticationException(ApplicationStatus status, String message) {
        super(status, message);
    }

    public AuthenticationException(ApplicationStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public AuthenticationException(ApplicationStatus status, Throwable cause) {
        super(status, cause);
    }
}
