package framework.core.enums;

/**
 * Unique codes that can be assigned as System Parameter.
 * 
 * @author Frederick Yap
 */
public enum ParameterCode {

    ANONYMOUS_USER_CREATION,

    /**
     * Represents the database version.
     */
    DB_VERSION,

    /**
     * Represents the email address used for sending e-mails.
     */
    EMAIL_ADDRESS,

    /**
     * Represents the POP host address used for sending e-mails.
     */
    EMAIL_HOST,

    /**
     * Represents the password used for authenticating to the SMTP.
     */
    EMAIL_PASSWORD,

    SESSION_TIMEOUT,

    /**
     * Represents if the SMTP requires authentication, TLS or SSL.
     */
    SMTP_AUTHENTICATION,

    /**
     * Represents the SMTP host for sending emails.
     */
    SMTP_HOST
}