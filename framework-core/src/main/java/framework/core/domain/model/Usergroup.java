package framework.core.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represents the usergroup that a user can be a member-of.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "USERGROUP")
public class Usergroup extends BaseEntity {

    private static final long serialVersionUID = -1885738464094155147L;

    @ManyToMany(fetch = FetchType.LAZY)
    @Auditable
    private List<Client> clients;

    @Column
    @Auditable
    private String description;

    @Column
    @Auditable
    private String name;

    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "USERGROUP_ROLE", joinColumns = @JoinColumn(name = "USERGROUP_ID", nullable = false))
    @Column(name = "NAME", nullable = false, length = 50)
    private List<String> roles;

    public List<Client> getClients() {
        if (this.clients == null) {
            this.clients = new ArrayList<Client>();
        }
        return this.clients;
    }

    /**
     * Returns the description of this usergroup.
     * 
     * @return the description of this usergroup.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the unique name of this usergroup.
     * 
     * @return the unique name of this usergroup.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the {@link Role}s that this usergroup have access to.
     * 
     * @return the {@link Role}s accessible to this usergroup.
     */
    public List<String> getRoles() {
        return this.roles;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}