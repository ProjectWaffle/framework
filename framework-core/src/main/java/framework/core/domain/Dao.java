package framework.core.domain;

import java.io.Serializable;

/**
 * This interface represents basic CRUD operations. All data access object interfaces must extends this interface.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must be an instance of {@link BaseEntity}.
 */
public interface Dao<T extends BaseEntity> extends Serializable {

    /**
     * Remove a persistent instance from the datastore.
     * 
     * @param t
     *            the persistent instance to be deleted.
     */
    void delete(T t);

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
}
