package framework.core.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.entity.User;
import framework.core.persistence.UserDao;

/**
 * Data access implementation for persisting {@link User} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final long serialVersionUID = -508553230014446994L;

    @Override
    public List<User> findUsersByName(String username) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return this.find("findUsersByName", parameters);
    }

}
