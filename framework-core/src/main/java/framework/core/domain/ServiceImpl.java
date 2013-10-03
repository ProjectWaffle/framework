package framework.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.EventType;
import framework.core.domain.auditlog.AuditlogService;

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
    
    private AuditlogService auditlogService;
    
    @Inject
    protected void setAuditlogService(AuditlogService auditlogService) {
        this.auditlogService = auditlogService;
    }

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
        T latest = this.persistence.findById(t.getId());
        if (latest != null) {
            if (latest.getVersion().equals(t.getVersion()) && !latest.isDeleted()) {
                t = latest;
            }
        }
        t.setDeleted(true);
        this.auditlogService.saveOrUpdate(t, EventType.DELETE);
        this.persistence.saveOrUpdate(t);
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
        T latest = this.persistence.findById(t.getId());
        if (latest != null) {
            if (latest.getVersion().equals(t.getVersion())) {
                this.auditlogService.saveOrUpdate(t, EventType.UPDATE);
                return this.persistence.saveOrUpdate(t);
            } else {
                return latest;
            }
        }
        this.auditlogService.saveOrUpdate(t, EventType.INSERT);
        return this.persistence.saveOrUpdate(t);
    }
    
}
