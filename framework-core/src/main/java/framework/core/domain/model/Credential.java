package framework.core.domain.model;

import java.security.Principal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents the credentials needed for system authentication.
 * 
 * @author frederick
 */
@Entity
@Table(name = "CREDENTIAL")
public class Credential extends BaseEntity implements Principal {

    private static final long serialVersionUID = -7767487387897790096L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Auditable
    private Client client;

    @Column(unique = true, nullable = false)
    @Auditable
    private String name;

    @Column
    @Encrypted
    private String password;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Auditable
    private Date passwordexpiration;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Auditable
    private Date profileexpiration;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Auditable
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @Auditable
    private Usergroup usergroup;

    public Client getClient() {
        return this.client;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the secret 'key' used by the user for authentication.
     * 
     * @return the secret 'key'.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the date the password is going to expire.
     * 
     * @return the password expiration date.
     */
    public Date getPasswordexpiration() {
        return this.passwordexpiration;
    }

    /**
     * Returns the date the user profile is going to expire.
     * 
     * @return the profile expiration date.
     */
    public Date getProfileexpiration() {
        return this.profileexpiration;
    }

    public User getUser() {
        return this.user;
    }

    /**
     * Returns the {@link Usergroup} of the user.
     * 
     * @return the {@link Usergroup}.
     */
    public Usergroup getUsergroup() {
        return this.usergroup;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordexpiration(Date passwordexpiration) {
        this.passwordexpiration = passwordexpiration;
    }

    public void setProfileexpiration(Date profileexpiration) {
        this.profileexpiration = profileexpiration;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsergroup(Usergroup usergroup) {
        this.usergroup = usergroup;
    }

}
