package framework.core.domain.repository;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import framework.core.domain.model.Auditlog;

@Named
class DefaultAuditlogRepository implements AuditlogRepository {

    private static final long serialVersionUID = -1534600147560699961L;
    private EntityManager entityManager;

    @PersistenceContext
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveOrUpdate(Auditlog auditlog) {
        this.entityManager.merge(auditlog);
    }

}
