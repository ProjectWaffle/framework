package framework.core.exceptions;


public class CredentialExpiredException extends AuthenticationException {

    private static final long serialVersionUID = -2298458254012445133L;

    public CredentialExpiredException(String message) {
        super(ApplicationStatus.EXPIRED_CREDENTIALS, message);
    }

    public CredentialExpiredException(String message, Throwable cause) {
        super(ApplicationStatus.EXPIRED_CREDENTIALS, message, cause);
    }

    public CredentialExpiredException(Throwable cause) {
        super(ApplicationStatus.EXPIRED_CREDENTIALS, cause);
    }

}
