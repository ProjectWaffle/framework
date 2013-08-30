package framework.core.domain.auditlog;

import java.util.List;

import framework.core.domain.Dao;

interface AuditlogDao extends Dao<Auditlog> {

    List<Auditlog> findLastAuditlogByDetail(String group);

}
