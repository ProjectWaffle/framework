package framework.core.exceptions;


public class PersistenceNotPossibleException extends ApplicationException {

    private static final long serialVersionUID = 4055419606888550827L;

    public PersistenceNotPossibleException(String message) {
        super(ApplicationStatus.SYSTEM_EXCEPTION, message);
    }

    public PersistenceNotPossibleException(String message, Throwable cause) {
        super(ApplicationStatus.SYSTEM_EXCEPTION, message, cause);
    }

    public PersistenceNotPossibleException(Throwable cause) {
        super(ApplicationStatus.SYSTEM_EXCEPTION, cause);
    }

}
