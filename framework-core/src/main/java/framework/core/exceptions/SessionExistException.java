package framework.core.exceptions;


public class SessionExistException extends ApplicationException {

    private static final long serialVersionUID = 95303143243413843L;

    public SessionExistException(String message) {
        super(ApplicationStatus.SESSION_ALREADY_EXIST, message);
    }

    public SessionExistException(String message, Throwable cause) {
        super(ApplicationStatus.SESSION_ALREADY_EXIST, cause);
    }

    public SessionExistException(Throwable cause) {
        super(ApplicationStatus.SESSION_ALREADY_EXIST, cause);
    }

}
