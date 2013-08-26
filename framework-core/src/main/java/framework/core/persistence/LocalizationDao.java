package framework.core.persistence;

import java.util.List;

import framework.core.entity.Localization;

public interface LocalizationDao extends Dao<Localization> {

    List<Localization> findByKeyAndLocale(String key, String locale);

}
