package framework.core.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This class contains basic CRUD implementation. All data access object classes must extends this class.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must be an instance of {@link BaseEntity}.
 */
@Named
public abstract class DaoImpl<T extends BaseEntity> implements Dao<T> {

    private static final long serialVersionUID = 4419440269704730290L;

    private final Class<T> persistentClass;

    protected EntityManager entityManager;

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    protected DaoImpl() {
        final Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.persistentClass = (Class<T>) types[0];
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#delete(framework.core.entity.AbstractEntity )
     */
    @Override
    public void delete(T t) {
        final T latest = this.entityManager.find(this.persistentClass, t.getId());
        if (latest != null) {
            this.entityManager.refresh(latest, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            if (latest.getVersion().equals(t.getVersion()) && !latest.isDeleted()) {
                t = latest;
            }
        }
        t.setDeleted(true);
        this.entityManager.merge(t);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#findById(java.lang.String)
     */
    @Override
    public T findById(String id) {
        final T t = this.entityManager.find(this.persistentClass, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        if (t != null) {
            this.entityManager.detach(t);
            return t;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#saveOrUpdate(framework.core.entity. AbstractEntity)
     */
    @Override
    public T saveOrUpdate(T t) {
        final T latest = this.entityManager.find(this.persistentClass, t.getId());
        if (latest != null) {
            this.entityManager.refresh(latest, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            if (latest.getVersion().equals(t.getVersion())) {
                return this.entityManager.merge(t);
            } else {
                return latest;
            }
        }
        return this.entityManager.merge(t);
    }

    protected boolean executeUpdate(String name) {
        return this.executeUpdate(name, null);
    }

    protected boolean executeUpdate(String name, Map<String, Object> parameters) {
        final Query query = this.entityManager.createNamedQuery(name);
        if (parameters != null) {
            for (final Entry<String, Object> parameter : parameters.entrySet()) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
        return query.executeUpdate() > 1;
    }

    protected List<T> find(String name) {
        return this.find(name, null, null, null);
    }

    protected List<T> find(String name, Map<String, Object> parameters) {
        return this.find(name, parameters, null);
    }

    protected List<T> find(String name, Map<String, Object> parameters, Integer index) {
        return this.find(name, parameters, index, null);
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String name, Map<String, Object> parameters, Integer index, Integer size) {
        final Query query = this.entityManager.createNamedQuery(name);
        if (parameters != null) {
            for (final Entry<String, Object> parameter : parameters.entrySet()) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
        if (index != null) {
            query.setFirstResult(index);
        }
        if (size != null) {
            query.setMaxResults(size);
        }
        final List<T> results = query.getResultList();
        for (final T t : results) {
            this.entityManager.detach(t);
        }
        return results;
    }

    /**
     * Sets an instance of {@link EntityManager} to be used by the data access object class. Override this method only
     * if using a different persistence unit aside from <em>defaultDb</em>.<br/>
     * <br/>
     * <em>WARNING!</em> This method must not be called directly.
     * 
     * @param entityManager
     *            instance of {@link EntityManager}.
     */
    @PersistenceContext
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}