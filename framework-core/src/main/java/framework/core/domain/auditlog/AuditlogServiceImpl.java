package framework.core.domain.auditlog;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;
import framework.core.domain.ServiceImpl;
import framework.core.exceptions.PersistenceNotPossibleException;

@Named
class AuditlogServiceImpl extends ServiceImpl<Auditlog> implements AuditlogService {

    private static final long serialVersionUID = 8337906203571696246L;
    
    @Inject
    protected AuditlogServiceImpl(AuditlogDao auditlogDao) {
        super(auditlogDao);
    }

    protected String getAuditlogDetail(BaseEntity entity) {
        StringBuilder log = new StringBuilder();
        try {
            String tableName = this.getClass().getSimpleName().toUpperCase();
            String details = generateDetailFields(entity);
            Table table = this.getClass().getAnnotation(Table.class);
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

    private String generateDetailFields(BaseEntity entity) throws IllegalAccessException {
        StringBuilder log = new StringBuilder();
        for (Field field : entity.getClass().getDeclaredFields()) {
            Auditable auditable = field.getAnnotation(Auditable.class);
            Column column = field.getAnnotation(Column.class);
            ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            if (auditable != null) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    if (column != null) {
                        String columnName = field.getName();
                        if (!"".equals(column.name())) {
                            columnName = column.name();
                        }
                        log.append(String.format("%s=\"%s\";", columnName.toUpperCase(), value));
                    } else if (manyToOne != null) {
                        log.append(generateRelationalFields((BaseEntity)value));
                    }
                }
            }
        }
        return log.toString();
    }

    private String generateRelationalFields(BaseEntity value) {
        StringBuilder log = new StringBuilder();
        String tableName = value.getClass().getSimpleName().toUpperCase();
        log.append(String.format("%s_ID=\"%s\";", tableName, value.getId()));
        return log.toString();
    }
}
