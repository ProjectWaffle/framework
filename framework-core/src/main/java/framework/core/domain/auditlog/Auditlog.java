package framework.core.domain.auditlog;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import framework.core.constants.EventType;
import framework.core.domain.BaseEntity;

/**
 * Represents events necessary for auditing.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "AUDITLOG")
public class Auditlog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3935171119789690953L;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private final Date logdate;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    public Auditlog() {
        this.logdate = Calendar.getInstance().getTime();
    }

    public Auditlog(EventType type) {
        this();
        this.type = type;
    }

    public Auditlog(String detail, EventType type) {
        this();
        this.detail = detail;
        this.type = type;
    }

    public Auditlog(Throwable e) {
        this();
        this.detail = String.format("%s - %s", e.getClass().getName(), e.getMessage());
        this.type = EventType.EXCEPTION;
    }

    /**
     * Returns the event log entry.
     * 
     * @return the event log entry.
     */
    public String getDetail() {
        return this.detail;
    }

    public Date getLogdate() {
        return this.logdate;
    }

    /**
     * Returns the event type.
     * 
     * @return the event type.
     */
    public EventType getType() {
        return this.type;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setType(EventType type) {
        this.type = type;
    }

}
