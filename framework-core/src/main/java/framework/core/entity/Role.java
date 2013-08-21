package framework.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USERGROUP_ROLE", joinColumns = {  @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USERGROUP_ID") })
    private List<Usergroup> roles;

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

}
