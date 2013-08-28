package framework.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * This class contains basic business operations. All service classes must extend from this class.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must extends {@link BaseEntity}.
 */
public interface Service<T extends BaseEntity> extends Serializable {

    /**
     * Delete persisted instances in the datastore.
     * 
     * @param ts
     *            persisted instances.
     */
    void delete(List<T> ts);

    /**
     * Delete persisted instances in the datastore.
     * 
     * @param t
     *            persisted instances.
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
     * Either save or update the given instances of an entity to the datastore.
     * 
     * @param ts
     *            the persisted instances.
     */
    List<T> saveOrUpdate(List<T> ts);

    /**
     * Either save or update the given instances of an entity to the datastore.
     * 
     * @param t
     *            the persisted instances.
     */
    T saveOrUpdate(T t);
}
