package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents the different roles that a {@link Usergroup} can have access to.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "ROLE")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 1759977741752483761L;

    public static final String ADMINISTRATOR = "Administrator";
    
    @Column
    private String description;

    @Column(unique = true)
    private String name;

    @Column
    private String urlPattern;

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

    /**
     * Returns the url pattern that the Role is accessible.
     * 
     * @return the url pattern.
     */
    public String getUrlPattern() {
        return this.urlPattern;
    }

}
