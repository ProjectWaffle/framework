package framework.core.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;

import framework.core.exceptions.UtilityException;

@Named
public class PropertiesUtil {

    private static final String ENVIRONMENT = "settings";
    private static final Properties PROPERTIES = new Properties();
    protected static final String ENCRYPTION_ASYMMERTRIC_KEYLENGTH = "framework.encryption.assymetric.keylength";
    protected static final String ENCRYPTION_ASYMMETRIC_ALGORITHM = "framework.encryption.assymetric.algorithm";
    protected static final String ENCRYPTION_ASYMMETRIC_SALT = "framework.encryption.assymetric.salt";
    protected static final String ENCRYPTION_ITERATION = "framework.encryption.iteration";
    protected static final String ENCRYPTION_SYMMETRIC_ALGORITHM = "framework.encryption.symmetric.algorithm";
    protected static final String ENCRYPTION_SYMMETRIC_PADDING = "framework.encryption.symmetric.padding";
    protected static final String ENCRYPTION_SYMMETRIC_SALT = "framework.encryption.symmetric.salt";

    protected PropertiesUtil() {
        final String location = System.getenv(ENVIRONMENT);
        try {
            if (location != null) {
                PROPERTIES.load(new FileInputStream(location));
            } else {
                PROPERTIES.load(PropertiesUtil.class.getResourceAsStream("/settings.properties"));
            }
        } catch (final IOException e) {
            throw new UtilityException("Unable to load properties file", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see framework.support.utilities.Pro#getProperty(java.lang.String)
     */
    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public boolean getBoolean(String key) {
        return Boolean.getBoolean(this.get(key));
    }

    public int getInteger(String key) {
        return Integer.valueOf(this.get(key));
    }

    public long getLong(String key) {
        return Long.valueOf(this.get(key));
    }

}