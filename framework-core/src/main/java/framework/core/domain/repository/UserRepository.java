package framework.core.domain.repository;

import java.util.List;

import framework.core.domain.model.Credential;

/**
 * Data access interface for persisting {@link Credential} entity.
 * 
 * @author Frederick Yap
 */
public interface UserRepository extends Repository<Credential> {

    List<Credential> findCredentialsByName(String username);
}
