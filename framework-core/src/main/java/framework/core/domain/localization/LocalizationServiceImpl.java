package framework.core.domain.localization;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;
import framework.core.exceptions.LocalizationException;

@Named
public class LocalizationServiceImpl extends ServiceImpl<Localization> implements LocalizationService {

    private static final long serialVersionUID = -6057911460335988890L;

    private final LocalizationDao localizationDao;

    @Inject
    protected LocalizationServiceImpl(LocalizationDao localizationDao) {
        super(localizationDao);
        this.localizationDao = localizationDao;
    }

    @Override
    public String findLocalization(String key, String locale) {
        final List<Localization> localizations = this.localizationDao.findByKeyAndLocale(key, locale);
        if (localizations.size() == 1) {
            return localizations.get(0).getValue();
        } else {
            throw new LocalizationException("Zero or multiple instance of localization found.");
        }
    }

}
