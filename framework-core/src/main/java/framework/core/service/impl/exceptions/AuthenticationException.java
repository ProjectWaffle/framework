package framework.core.service.impl.exceptions;

import framework.core.constants.ApplicationStatus;

public abstract class AuthenticationException extends ServiceException {

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
