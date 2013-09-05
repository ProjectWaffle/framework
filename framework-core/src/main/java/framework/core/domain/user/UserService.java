package framework.core.domain.user;

import framework.core.domain.Service;
import framework.core.domain.session.Session;

/**
 * This interface represents business operations for {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
public interface UserService extends Service<Credential> {

    Session authenticate(String username, String password);

    Credential findCredentialByUsername(String username);

    void logout(Credential user);

    void logoutExpiredSession();

    Credential saveOrUpdate(Credential user, String password);
}
