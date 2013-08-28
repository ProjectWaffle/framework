package framework.core.domain.localization;

import framework.core.domain.Service;

public interface LocalizationService extends Service<Localization> {

    String findLocalization(String key, String locale);

}
