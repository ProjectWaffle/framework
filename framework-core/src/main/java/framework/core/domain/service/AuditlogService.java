package framework.core.domain.service;

import java.io.Serializable;

import framework.core.domain.model.BaseEntity;
import framework.core.domain.model.EventType;

public interface AuditlogService extends Serializable {

    void saveOrUpdate(BaseEntity entity, EventType type);

    void saveOrUpdate(EventType eventType);

    void saveOrUpdate(Exception exception);

    void saveOrUpdate(String detail, EventType type);
}
