package framework.support.utilities;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import com.thoughtworks.xstream.XStream;

import framework.core.utilities.XMLEncoder;

@Named
public class XMLEncoderImpl implements XMLEncoder {

    @Override
    public <T> T convert(InputStream xml, Class<?>... type) {
        Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        for (final Class<?> aClass : type) {
            map.put(aClass.getSimpleName(), aClass);
        }
        return this.convert(xml, map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(InputStream xml, Map<String, Class<?>> map) {
        final XStream xStream = new XStream();
        for (final Entry<String, Class<?>> entry : map.entrySet()) {
            xStream.alias(entry.getKey(), entry.getValue());
        }
        this.registerCommonAlias(xStream);
        return (T) xStream.fromXML(xml);
    }

    private void registerCommonAlias(final XStream xStream) {
        xStream.alias("List", List.class);
        xStream.alias("list", List.class);
        xStream.alias("map", Map.class);
        xStream.alias("Map", Map.class);
    }

}
