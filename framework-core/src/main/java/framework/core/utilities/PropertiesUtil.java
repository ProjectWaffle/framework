package framework.core.utilities;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PropertiesUtil {

    private final Properties properties;

    @Inject
    protected PropertiesUtil(Properties propertiesReader) {
        this.properties = propertiesReader;
    }

    /*
     * (non-Javadoc)
     * @see framework.support.utilities.Pro#getProperty(java.lang.String)
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

}