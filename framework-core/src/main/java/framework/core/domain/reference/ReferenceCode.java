package framework.core.domain.reference;

/**
 * Unique codes that can be assigned as System Parameter.
 * 
 * @author Frederick Yap
 */
public abstract class ReferenceCode {

    public static final String CONFIGURATION_ANONYMOUS_USER_CREATION = "ANONYMOUS_USER_CREATION";

    /**
     * Represents the database version.
     */
    public static final String CONFIGURATION_DB_VERSION = "DB_VERSION";

    /**
     * Represents the email address used for sending e-mails.
     */
    public static final String CONFIGURATION_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    /**
     * Represents the POP host address used for sending e-mails.
     */
    public static final String CONFIGURATION_EMAIL_HOST = "EMAIL_HOST";

    /**
     * Represents the password used for authenticating to the SMTP.
     */
    public static final String CONFIGURATION_EMAIL_PASSWORD = "EMAIL_PASSWORD";

    public static final String CONFIGURATION_SESSION_TIMEOUT = "SESSION_TIMEOUT";

    /**
     * Represents if the SMTP requires authentication = ""; TLS or SSL.
     */
    public static final String CONFIGURATION_SMTP_AUTHENTICATION = "SMTP_AUTHENTICATION";

    /**
     * Represents the SMTP host for sending emails.
     */
    public static final String CONFIGURATION_SMTP_HOST = "SMTP_HOST";
}