package framework.core.domain.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;
import framework.core.domain.usergroup.Usergroup;

/**
 * Represents the different roles that a {@link Usergroup} can have access to.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {

    public static final String ADMINISTRATORS = "Administrators";
    public static final String USERS = "Users";

    private static final long serialVersionUID = 1759977741752483761L;

    @Column
    private String description;

    @Column(unique = true)
    private String name;

    /**
     * Returns the description of this Role.
     * 
     * @return the description of this Role.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the unique name given to this Role.
     * 
     * @return the unique name.
     */
    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
