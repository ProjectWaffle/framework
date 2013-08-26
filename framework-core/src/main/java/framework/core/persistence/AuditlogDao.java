package framework.core.persistence;

import java.util.List;

import framework.core.entity.Auditlog;

public interface AuditlogDao extends Dao<Auditlog> {

    List<Auditlog> findLastAuditlogByDetail(String group);

}
