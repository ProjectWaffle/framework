package framework.core.domain.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import framework.core.constants.ReferenceType;
import framework.core.domain.BaseEntity;

/**
 * Represents the <em>look-up</em> values used by the system.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "REFERENCE")
public class Reference extends BaseEntity {

    private static final long serialVersionUID = -6616038947469958259L;

    @Column
    private String code;

    @Column
    private String label;

    @Column
    @Enumerated(EnumType.STRING)
    private ReferenceType type;

    /**
     * Returns the unique code.
     * 
     * @return the unique code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the display label.
     * 
     * @return the display label.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the reference type.
     * 
     * @return the reference type.
     */
    public ReferenceType getType() {
        return this.type;
    }

}
