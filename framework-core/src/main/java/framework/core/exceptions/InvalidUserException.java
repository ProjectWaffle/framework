package framework.core.exceptions;


public class InvalidUserException extends AuthenticationException {

    private static final long serialVersionUID = 2282329412806106927L;

    public InvalidUserException(String message) {
        super(ApplicationStatus.INVALID_USER, message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(ApplicationStatus.INVALID_USER, message, cause);
    }

    public InvalidUserException(Throwable cause) {
        super(ApplicationStatus.INVALID_USER, cause);
    }

}
