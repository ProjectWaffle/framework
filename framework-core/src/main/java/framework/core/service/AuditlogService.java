package framework.core.service;

import framework.core.entity.Auditlog;

public interface AuditlogService extends Service<Auditlog> {

    Auditlog findLastAuditlogByCurrentDetail(String detail);

}
