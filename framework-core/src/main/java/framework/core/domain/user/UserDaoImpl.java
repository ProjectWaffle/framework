package framework.core.domain.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

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
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return this.find("findCredentialsByName", parameters);
    }

}
