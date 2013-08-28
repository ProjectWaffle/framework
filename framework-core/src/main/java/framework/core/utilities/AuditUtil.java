package framework.core.utilities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import framework.core.constants.EventType;
import framework.core.domain.BaseEntity;
import framework.core.domain.auditlog.Auditlog;
import framework.core.domain.auditlog.AuditlogService;

@Named
public class AuditUtil implements Serializable {

    private static AuditlogService auditlogService;

    private static final long serialVersionUID = 1819000638364499266L;

    private String generateAuditlogDetails(BaseEntity entity) {
        final StringBuilder detail = new StringBuilder();
        final Table table = entity.getClass().getAnnotation(Table.class);
        try {
            if (table != null) {
                if ("".equals(table.name().trim())) {
                    detail.append(entity.getClass().getSimpleName());
                } else {
                    detail.append(table.name());
                }
                detail.append("[");
                detail.append("id=");
                detail.append("\"");
                detail.append(entity.getId());
                detail.append("\", ");
                detail.append(this.getAuditlogFieldDetails(entity));
                detail.delete(detail.lastIndexOf(","), detail.length());
                detail.append("]");
            }
            return detail.toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Unable to generate auditlog detail.", e);
        }
    }

    private StringBuilder getAuditlogFieldDetails(BaseEntity entity) throws IllegalAccessException {
        final Field[] fields = entity.getClass().getDeclaredFields();
        final StringBuilder fieldDetails = new StringBuilder();
        for (final Field field : fields) {
            field.setAccessible(true);
            final ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
            if (manyToMany != null) {
                @SuppressWarnings("unchecked")
                final Collection<BaseEntity> collection = (Collection<BaseEntity>) field.get(entity);
                if (collection != null) {
                    final Iterator<BaseEntity> iterator = collection.iterator();
                    fieldDetails.append("ITEMS={\"");
                    while (iterator.hasNext()) {
                        fieldDetails.append(this.generateAuditlogDetails(iterator.next()));
                        fieldDetails.append("\", ");
                    }
                    fieldDetails.delete(fieldDetails.lastIndexOf(","), fieldDetails.length());
                    fieldDetails.append("]");
                    fieldDetails.append("}\", ");
                }
            }
            final Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if ("".equals(column.name())) {
                    fieldDetails.append(field.getName());
                } else {
                    fieldDetails.append(column.name());
                }
                fieldDetails.append("=\"");
                fieldDetails.append(field.get(entity));
                fieldDetails.append("\", ");
            }
            final JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
            if (joinColumn != null) {
                if ("".equals(joinColumn.name())) {
                    fieldDetails.append(field.getName());
                } else {
                    fieldDetails.append(joinColumn.name());
                }
                fieldDetails.append("=\"");
                fieldDetails.append(field.get(entity));
                fieldDetails.append("\", ");
            }
            final ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            if (manyToOne != null) {
                final BaseEntity abstractEntity = (BaseEntity) field.get(entity);
                if (abstractEntity != null) {
                    fieldDetails.append(abstractEntity.getClass().getSimpleName().toUpperCase().replace("_$$_JAVASSIST_1", "") + "_ID");
                    fieldDetails.append("=\"");
                    fieldDetails.append(abstractEntity.getId());
                    fieldDetails.append("\", ");
                }
            }
        }
        return fieldDetails;
    }

    @PrePersist
    protected void onPersist(BaseEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            auditlog.setDetail(detail);
            auditlog.setType(EventType.INSERT);
            auditlogService.saveOrUpdate(auditlog);
        }
    }

    @PreRemove
    protected void onRemove(BaseEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            auditlog.setDetail(detail);
            auditlog.setType(EventType.DELETE);
            auditlogService.saveOrUpdate(auditlog);
        }

    }

    @PreUpdate
    protected void onUpdate(BaseEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            auditlog.setDetail(detail);
            if (entity.isDeleted()) {
                auditlog.setType(EventType.DELETE);
            } else {
                auditlog.setType(EventType.UPDATE);
            }
            auditlogService.saveOrUpdate(auditlog);
        }
    }

    @Inject
    protected void setAuditlogService(AuditlogService auditlogService) {
        AuditUtil.auditlogService = auditlogService;
    }
    
}