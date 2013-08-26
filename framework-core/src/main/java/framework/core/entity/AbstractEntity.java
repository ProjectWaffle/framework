package framework.core.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import framework.core.utilities.AuditlogUtil;

/**
 * This class provides basic implementations for all Entity classes.
 * 
 * @author Frederick Yap
 */
@MappedSuperclass
@EntityListeners(value = { AuditlogUtil.class })
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2688529713266301979L;

    @Column
    private boolean deleted;

    @Id
    @Column
    private final String id;

    @Column
    @Version
    private Long version;

    protected AbstractEntity() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Returns the unique identifier for this data.
     * 
     * @return the unique identifier.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the version of this data used for pessimistic locking.
     * 
     * @return the version of this data.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Returns the status of this data.
     * 
     * @return true if this is marked as deleted.
     */
    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
