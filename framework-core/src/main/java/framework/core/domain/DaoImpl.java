package framework.core.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQuery;
    private EntityManager entityManager;
    private Root<T> fromRoot;
    private final Class<T> persistentClass;

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
     * @see framework.core.persistence.Dao#findById(java.lang.String)
     */
    @Override
    public T findById(String id) {
        final T t = this.entityManager.find(this.persistentClass, id);
        this.entityManager.clear();
        return t;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#saveOrUpdate(framework.core.entity. AbstractEntity)
     */
    @Override
    public T saveOrUpdate(T t) {
        return this.entityManager.merge(t);
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return this.criteriaBuilder;
    }

    protected List<T> getResultList() {
        return this.getResultList(false, null);
    }

    protected List<T> getResultList(boolean isDeleted, Predicate condition) {
        final Predicate isdeletedCondition = this.criteriaBuilder.equal(this.fromRoot.get("deleted"), isDeleted);
        if (condition != null) {
            this.criteriaQuery.where(this.criteriaBuilder.and(condition, isdeletedCondition));
        } else {
            this.criteriaQuery.where(isdeletedCondition);
        }
        final List<T> results = this.entityManager.createQuery(this.criteriaQuery).getResultList();
        this.entityManager.clear();
        return results;
    }

    protected List<T> getResultList(Predicate condition) {
        return this.getResultList(false, condition);
    }

    protected Root<T> getRoot() {
        this.criteriaQuery = this.criteriaBuilder.createQuery(this.persistentClass);
        this.fromRoot = this.criteriaQuery.from(this.persistentClass);
        return this.fromRoot;
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
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }
}