package framework.core.domain.localization;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.DaoImpl;

@Named
class LocalizationDaoImpl extends DaoImpl<Localization> implements LocalizationDao {

    private static final long serialVersionUID = -1810597520079717978L;

    @Override
    public List<Localization> findByKeyAndLocale(String key, String value) {
        Root<Localization> fromLocalization = getRoot();
        Predicate condition1 = getCriteriaBuilder().equal(fromLocalization.get("key"), key);
        Predicate condition2 = getCriteriaBuilder().equal(fromLocalization.get("value"), value);
        return this.getResultList(condition1, condition2);
    }

}
