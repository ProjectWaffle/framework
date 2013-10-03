package framework.core.domain.auditlog;

import java.io.Serializable;

interface AuditlogDao extends Serializable {

    void saveOrUpdate(Auditlog auditlog);
}
