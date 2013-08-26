package framework.core.service.impl.exceptions;

import framework.core.constants.ApplicationStatus;

public class UserProfileExpiredException extends AuthenticationException {

    private static final long serialVersionUID = -1238140812607788639L;

    public UserProfileExpiredException(String message) {
        super(ApplicationStatus.EXPIRED_PROFILE, message);
    }

    public UserProfileExpiredException(String message, Throwable cause) {
        super(ApplicationStatus.EXPIRED_PROFILE, message, cause);
    }

    public UserProfileExpiredException(Throwable cause) {
        super(ApplicationStatus.EXPIRED_PROFILE, cause);
    }

}
