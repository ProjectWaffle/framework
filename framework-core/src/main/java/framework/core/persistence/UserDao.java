package framework.core.persistence;

import java.util.List;

import framework.core.entity.User;

/**
 * Data access interface for persisting {@link User} entity.
 * 
 * @author Frederick Yap
 */
public interface UserDao extends Dao<User> {

    List<User> findUsersByName(String username);
}
