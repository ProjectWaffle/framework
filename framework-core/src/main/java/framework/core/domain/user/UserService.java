package framework.core.domain.user;

import framework.core.domain.Service;

/**
 * This interface represents business operations for {@link User} entity.
 * 
 * @author Frederick Yap
 */
public interface UserService extends Service<User> {

    String authenticate(String username, String password);
    
    User findUserByUsername(String username);
}
