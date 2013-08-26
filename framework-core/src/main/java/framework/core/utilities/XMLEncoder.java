package framework.core.utilities;

import java.io.InputStream;
import java.util.Map;

import javax.xml.crypto.Data;

/**
 * Utility for parsing XML to POJO. User must provide necessary implementation.
 * 
 * @author Frederick Yap
 */
public interface XMLEncoder {

    /**
     * Converts XML to {@link Data} class.
     * 
     * @param xml
     *            {@link InputStream} containing the XML file.
     * @param types
     *            types to be used as alias.
     * @return converted XML to {@link Data} class.
     */
    <T> T convert(InputStream xml, Class<?>... types);

    /**
     * Converts XML to {@link Data} class.
     * 
     * @param xml
     *            {@link InputStream} containing the XML file.
     * @param types
     *            types to be used as alias.
     * @return converted XML to {@link Data} class.
     */
    <T> T convert(InputStream xml, Map<String, Class<?>> type);
}
