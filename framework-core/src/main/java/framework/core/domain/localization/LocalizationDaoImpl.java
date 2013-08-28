package framework.core.domain.localization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
public class LocalizationDaoImpl extends DaoImpl<Localization> implements LocalizationDao {

    private static final long serialVersionUID = -1810597520079717978L;

    @Override
    public List<Localization> findByKeyAndLocale(String key, String locale) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("key", key);
        parameters.put("locale", locale);
        return this.find("findByKeyAndLocale", parameters);
    }

}
