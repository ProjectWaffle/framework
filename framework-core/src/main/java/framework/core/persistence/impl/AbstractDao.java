package framework.core.persistence.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import framework.core.entity.AbstractEntity;
import framework.core.persistence.Dao;

/**
 * This class contains basic CRUD implementation. All data access object classes must extends this class.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must be an instance of {@link AbstractEntity}.
 */
@Named
public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    private static final long serialVersionUID = 4419440269704730290L;

    private final Class<T> persistentClass;

    protected EntityManager entityManager;

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        final Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.persistentClass = (Class<T>) types[0];
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#delete(framework.core.entity.AbstractEntity )
     */
    @Override
    public void delete(T t) {
        t.setDeleted(true);
        this.saveOrUpdate(t);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#findById(java.lang.String)
     */
    @Override
    public T findById(String id) {
        final T t = this.entityManager.find(this.persistentClass, id);
        if (t != null) {
            this.entityManager.detach(t);
            if (!t.isDeleted()) {
                return t;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#saveOrUpdate(framework.core.entity. AbstractEntity)
     */
    @Override
    public T saveOrUpdate(T t) {
        return this.entityManager.merge(t);
    }

    protected List<T> find(String name) {
        return this.find(name, null, null, null, false);
    }

    protected List<T> find(String name, boolean isCacheable) {
        return this.find(name, null, null, null, isCacheable);
    }

    protected List<T> find(String name, Map<String, Object> parameters) {
        return this.find(name, parameters, null, false);
    }

    protected List<T> find(String name, Map<String, Object> parameters, boolean isCacheable) {
        return this.find(name, parameters, null, null, isCacheable);
    }

    protected List<T> find(String name, Map<String, Object> parameters, Integer index) {
        return this.find(name, parameters, index, null, false);
    }

    protected List<T> find(String name, Map<String, Object> parameters, Integer index, boolean isCacheable) {
        return this.find(name, parameters, index, null, isCacheable);
    }

    protected List<T> find(String name, Map<String, Object> parameters, Integer index, Integer size) {
        return this.find(name, parameters, index, size, false);
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String name, Map<String, Object> parameters, Integer index, Integer size, boolean isCacheable) {
        final List<T> results = new ArrayList<T>();
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
        for (final T t : (List<T>) query.getResultList()) {
            this.entityManager.detach(t);
            if (!t.isDeleted()) {
                results.add(t);
            }
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