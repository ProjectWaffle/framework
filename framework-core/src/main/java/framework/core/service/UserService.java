package framework.core.service;

import framework.core.entity.User;

/**
 * This interface represents business operations for {@link User} entity.
 * 
 * @author Frederick Yap
 */
public interface UserService extends Service<User> {

    String authenticate(String username, String password);
    
    User findUserByUsername(String username);
}
