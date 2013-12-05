package framework.core.domain.auditlog;

import java.io.Serializable;

import framework.core.domain.BaseEntity;

public interface AuditlogService extends Serializable {

    void saveOrUpdate(BaseEntity entity, EventType type);

    void saveOrUpdate(EventType eventType);

    void saveOrUpdate(Exception exception);

    void saveOrUpdate(String detail, EventType type);
}
