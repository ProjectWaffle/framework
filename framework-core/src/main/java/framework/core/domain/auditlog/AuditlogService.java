package framework.core.domain.auditlog;

import framework.core.domain.Service;

public interface AuditlogService extends Service<Auditlog> {

    Auditlog findLastAuditlogByCurrentDetail(String detail);

}
