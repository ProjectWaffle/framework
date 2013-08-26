package framework.core.service;

import framework.core.entity.Localization;

public interface LocalizationService extends Service<Localization> {

    String findLocalization(String key, String locale);

}
