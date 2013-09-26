package framework.core.domain.user;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.DaoImpl;

/**
 * Data access implementation for persisting {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
@Named
class UserDaoImpl extends DaoImpl<Credential> implements UserDao {

    private static final long serialVersionUID = -508553230014446994L;

    @Override
    public List<Credential> findCredentialsByName(String username) {
        Root<Credential> fromUser = this.getRoot();
        Predicate condition = getCriteriaBuilder().equal(fromUser.get("name"), username);
        return this.getResultList(condition);
    }

}
