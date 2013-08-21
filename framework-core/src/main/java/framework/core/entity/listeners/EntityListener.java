package framework.core.entity.listeners;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Auditlog;
import framework.core.enums.EventType;
import framework.core.service.AuditlogService;
import framework.core.utilities.DateUtils;

@Named
public class EntityListener implements Serializable {

    private static AuditlogService auditlogService;
    private static DateUtils dateUtils;

    private static final long serialVersionUID = 1819000638364499266L;

    private String generateAuditlogDetails(AbstractEntity entity) {
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

    private StringBuilder getAuditlogFieldDetails(AbstractEntity entity) throws IllegalAccessException {
        final Field[] fields = entity.getClass().getDeclaredFields();
        final StringBuilder fieldDetails = new StringBuilder();
        for (final Field field : fields) {
            field.setAccessible(true);
            final ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
            if (manyToMany != null) {
                @SuppressWarnings("unchecked")
                final Collection<AbstractEntity> collection = (Collection<AbstractEntity>) field.get(entity);
                if (collection != null) {
                    final Iterator<AbstractEntity> iterator = collection.iterator();
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
        }
        return fieldDetails;
    }

    @PrePersist
    protected void onPersist(AbstractEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            auditlog.setLogdate(dateUtils.getCurrentUnixTime());
            auditlog.setDetail(detail);
            auditlog.setType(EventType.INSERT);
            auditlogService.saveOrUpdate(auditlog);
        }
    }

    @PreRemove
    protected void onRemove(AbstractEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            auditlog.setLogdate(dateUtils.getCurrentUnixTime());
            auditlog.setDetail(detail);
            auditlog.setType(EventType.DELETE);
            auditlogService.saveOrUpdate(auditlog);
        }
    }

    @PreUpdate
    protected void onUpdate(AbstractEntity entity) {
        if (!(entity instanceof Auditlog)) {
            final Auditlog auditlog = new Auditlog();
            final String detail = this.generateAuditlogDetails(entity);
            final Auditlog previousLog = auditlogService.findLastAuditlogByCurrentDetail(detail);
            auditlog.setLogdate(dateUtils.getCurrentUnixTime());
            auditlog.setDetail(detail);
            auditlog.setType(EventType.UPDATE);
            if (previousLog != null) {
                auditlog.setPrevious(previousLog.getDetail());
                auditlogService.saveOrUpdate(auditlog);
            }
        }
    }

    @Inject
    protected void setAuditlogService(AuditlogService auditlogService) {
        EntityListener.auditlogService = auditlogService;
    }

    @Inject
    protected void setDateUtils(DateUtils dateUtils) {
        EntityListener.dateUtils = dateUtils;
    }
}
