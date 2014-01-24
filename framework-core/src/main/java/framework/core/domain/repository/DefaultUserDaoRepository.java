package framework.core.domain.repository;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.model.Credential;

/**
 * Data access implementation for persisting {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
@Named
class DefaultUserDaoRepository extends AbstractRepository<Credential> implements UserRepository {

    private static final long serialVersionUID = -508553230014446994L;

    @Override
    public List<Credential> findCredentialsByName(String username) {
        Root<Credential> fromUser = this.getRoot();
        Predicate condition1 = getCriteriaBuilder().equal(fromUser.get("name"), username);
        return this.getResultList(condition1);
    }

}
