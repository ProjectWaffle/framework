package framework.core.domain.user;

import java.util.List;

import framework.core.domain.Dao;

/**
 * Data access interface for persisting {@link User} entity.
 * 
 * @author Frederick Yap
 */
public interface UserDao extends Dao<User> {

    List<User> findUsersByName(String username);
}
