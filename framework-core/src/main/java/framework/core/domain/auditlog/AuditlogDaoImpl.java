package framework.core.domain.auditlog;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
class AuditlogDaoImpl implements AuditlogDao {

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
