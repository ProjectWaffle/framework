package framework.core.domain.repository;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.model.Client;
import framework.core.domain.model.Configuration;
import framework.core.domain.model.Reference;

@Named
class DefaultConfigurationRepository extends AbstractRepository<Configuration> implements ConfigurationRepository {

    private static final long serialVersionUID = -7635229511504452985L;

    @Override
    public     Configuration  findConfigurationByCodeAndClient(String code, String name) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Reference> joinReference = fromConfiguration.join("reference", JoinType.INNER);
        Join<Configuration, Client> joinClient= fromConfiguration.join("client", JoinType.INNER);
        Predicate condition = getCriteriaBuilder().and(
                getCriteriaBuilder().equal(joinReference.get("code"), code), 
                getCriteriaBuilder().equal(joinClient.get("name"), name));
        return this.getResult(condition);
    }

    @Override
    public     Configuration  findConfigurationByCode(String code) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Reference> joinReference = fromConfiguration.join("reference", JoinType.INNER);
        Predicate condition = getCriteriaBuilder().equal(joinReference.get("code"), code);
        return this.getResult(condition);
    }

    @Override
    public         List<Configuration>  findAllActiveConfiguration(String name) {
        Root<Configuration> fromConfiguration = getRoot();
        Join<Configuration, Client> joinClient= fromConfiguration.join("client", JoinType.INNER);
        Predicate condition = getCriteriaBuilder().equal(joinClient.get("name"), name);
        return this.getResultList(condition);
    }

}
