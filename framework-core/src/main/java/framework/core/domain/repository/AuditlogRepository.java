package framework.core.domain.repository;

import java.io.Serializable;

import framework.core.domain.model.Auditlog;

public interface AuditlogRepository extends Serializable {

    void saveOrUpdate(Auditlog auditlog);
}
