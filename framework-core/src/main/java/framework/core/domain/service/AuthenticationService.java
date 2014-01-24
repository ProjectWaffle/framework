package framework.core.domain.service;

import java.io.Serializable;

import framework.core.domain.model.Credential;
import framework.core.domain.model.Session;

/**
 * This interface represents business operations for {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
public interface AuthenticationService extends Serializable {

    Session authenticate(String username, String password);

    Credential findCredentialByUsername(String username);

    void logout(Credential user);

    void logoutExpiredSession();
    
    Session saveOrUpdate(Credential credential);
    
    Session findSessionById(String username, String id);
}
