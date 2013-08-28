package framework.core.domain.localization;

import java.util.List;

import framework.core.domain.Dao;

public interface LocalizationDao extends Dao<Localization> {

    List<Localization> findByKeyAndLocale(String key, String locale);

}
