package framework.core.domain.user;

import java.util.List;

import framework.core.domain.Dao;

/**
 * Data access interface for persisting {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
interface UserDao extends Dao<Credential> {

    List<Credential> findCredentialsByName(String username);
}
