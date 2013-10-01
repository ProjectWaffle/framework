package framework.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * Provides basic business operation for all service classes.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must extends {@link BaseEntity}.
 * @param <I>
 *            must extends {@link Dao} of type T.
 */
@Named
public abstract class ServiceImpl<T extends BaseEntity> implements Service<T> {

    private static final long serialVersionUID = 8443877242673541465L;

    private final Dao<T> persistence;

    protected ServiceImpl(Dao<T> persistence) {
        this.persistence = persistence;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#delete(java.util.Collection)
     */
    @Override
    public final void delete(List<T> ts) {
        for (final T t : ts) {
            this.delete(t);
        }
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#delete(T t)
     */
    @Override
    public final void delete(T t) {
        this.persistence.delete(t);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#findById(java.lang.String)
     */
    @Override
    public final T findById(String id) {
        return this.persistence.findById(id);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#saveOrUpdate(java.util.Collection)
     */
    @Override
    public final List<T> saveOrUpdate(List<T> ts) {
        final List<T> list = new ArrayList<T>();
        for (final T t : ts) {
            list.add(this.saveOrUpdate(t));
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#saveOrUpdate(T t)
     */
    @Override
    public final T saveOrUpdate(T t) {
        return this.persistence.saveOrUpdate(t);
    }
    
}
