package framework.core.exceptions;

import framework.core.enums.ApplicationStatus;

public class LocalizationException extends ServiceException {

    private static final long serialVersionUID = 4765745720114024395L;

    public LocalizationException(String message) {
        super(ApplicationStatus.FAILED_TO_LOCALIZED, message);
    }

    public LocalizationException(String message, Throwable cause) {
        super(ApplicationStatus.FAILED_TO_LOCALIZED, message, cause);
    }

    public LocalizationException(Throwable cause) {
        super(ApplicationStatus.FAILED_TO_LOCALIZED, cause);
    }

}
