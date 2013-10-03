package framework.core.domain.auditlog;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import framework.core.constants.EventType;
import framework.core.domain.BaseEntity;
import framework.core.exceptions.PersistenceNotPossibleException;

@Named
class AuditlogServiceImpl implements AuditlogService {

    private static final long serialVersionUID = 8337906203571696246L;
    private final AuditlogDao auditlogDao;

    @Inject
    protected AuditlogServiceImpl(AuditlogDao auditlogDao) {
        this.auditlogDao = auditlogDao;
    }

    @Override
    public void saveOrUpdate(BaseEntity entity, EventType type) {
        final Auditlog auditlog = new Auditlog(this.getAuditlogDetail(entity), type);
        this.auditlogDao.saveOrUpdate(auditlog);
    }

    @Override
    public void saveOrUpdate(EventType eventType) {
        final Auditlog auditlog = new Auditlog(eventType);
        this.auditlogDao.saveOrUpdate(auditlog);
    }

    @Override
    public void saveOrUpdate(Exception exception) {
        final Auditlog auditlog = new Auditlog(exception);
        this.auditlogDao.saveOrUpdate(auditlog);
    }

    @Override
    public void saveOrUpdate(String detail, EventType type) {
        final Auditlog auditlog = new Auditlog(detail, type);
        this.auditlogDao.saveOrUpdate(auditlog);
    }

    private String generateDetailFields(BaseEntity entity) throws IllegalAccessException {
        final StringBuilder log = new StringBuilder();
        for (final Field field : entity.getClass().getDeclaredFields()) {
            final Auditable auditable = field.getAnnotation(Auditable.class);
            final Column column = field.getAnnotation(Column.class);
            final ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            if (auditable != null) {
                field.setAccessible(true);
                final Object value = field.get(entity);
                if (value != null) {
                    if (column != null) {
                        String columnName = field.getName();
                        if (!"".equals(column.name())) {
                            columnName = column.name();
                        }
                        log.append(String.format("%s=\"%s\";", columnName.toUpperCase(), value));
                    } else if (manyToOne != null) {
                        log.append(this.generateRelationalFields((BaseEntity) value));
                    }
                }
            }
        }
        return log.toString();
    }

    private String generateRelationalFields(BaseEntity value) {
        final StringBuilder log = new StringBuilder();
        final String tableName = value.getClass().getSimpleName().toUpperCase();
        log.append(String.format("%s_ID=\"%s\";", tableName, value.getId()));
        return log.toString();
    }

    private String getAuditlogDetail(BaseEntity entity) {
        final StringBuilder log = new StringBuilder();
        try {
            String tableName = entity.getClass().getSimpleName().toUpperCase();
            final String details = this.generateDetailFields(entity);
            final Table table = this.getClass().getAnnotation(Table.class);
            if (table != null) {
                if (!"".equals(table.name())) {
                    tableName = table.name();
                }
            }
            log.append(String.format("%s[", tableName));
            log.append(String.format("ID=\"%s\";", entity.getId()));
            log.append(details);
            log.deleteCharAt(log.length() - 1);
            log.append("]");
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new PersistenceNotPossibleException("Unable to generate auditlog details.", e);
        }
        return log.toString();
    }
}
