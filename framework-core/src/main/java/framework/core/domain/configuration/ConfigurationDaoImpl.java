package framework.core.domain.configuration;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.DaoImpl;
import framework.core.domain.client.Client;
import framework.core.domain.reference.Reference;

@Named
class ConfigurationDaoImpl extends DaoImpl<Configuration> implements ConfigurationDao {

    private static final long serialVersionUID = -7635229511504452985L;

    @Override
    public List<Configuration> findConfigurationByCodeAndClient(String code, String name) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Reference> joinReference = fromConfiguration.join("reference", JoinType.INNER);
        Join<Configuration, Client> joinClient= fromConfiguration.join("client", JoinType.INNER);
        Predicate condition1 = getCriteriaBuilder().equal(joinReference.get("code"), code);
        Predicate condition2 = getCriteriaBuilder().equal(joinClient.get("name"), name);
        return this.getResultList(condition1, condition2);
    }

    @Override
    public List<Configuration> findConfigurationByCode(String code) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Reference> joinReference = fromConfiguration.join("reference", JoinType.INNER);
        Predicate condition1 = getCriteriaBuilder().equal(joinReference.get("code"), code);
        return this.getResultList(condition1);
    }

    @Override
    public List<Configuration> findAllActiveConfiguration(String name) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Client> joinClient= fromConfiguration.join("client", JoinType.INNER);
        Predicate condition1 = getCriteriaBuilder().equal(joinClient.get("name"), name);
        return this.getResultList(condition1);
    }

}
