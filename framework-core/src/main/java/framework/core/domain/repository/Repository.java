package framework.core.domain.repository;

import java.io.Serializable;
import java.util.List;

import framework.core.domain.model.BaseEntity;

/**
 * This interface represents basic CRUD operations. All data access object interfaces must extends this interface.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must be an instance of {@link BaseEntity}.
 */
interface Repository<T extends BaseEntity> extends Serializable {

    /**
     * Return the persistent instance of the given entity class with the given identifier, or null if there is no such
     * persistent instance.
     * 
     * @param id
     *            the unique identifier of the entity class.
     * @return the persistent instance of the given entity class with the given identifier.
     */
    T findById(String id);

    /**
     * Either save or update the given instance of an entity to the datastore.
     * 
     * @param t
     *            the persisted instance.
     * @return the updated persisted instance.
     */
    T saveOrUpdate(T t);
    
    void saveOrUpdate(List<T> ts);
    
    void delete(T t);
    
    void delete(List<T> ts);
}
