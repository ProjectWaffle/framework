package framework.core.domain.session;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import framework.core.domain.BaseEntity;
import framework.core.domain.user.Credential;

@Entity
@Table(name = "SESSION")
@NamedQueries(value = {
        @NamedQuery(name = "findActiveSessionById", query = "from Session s where s.id =:id and s.deleted = false"),
        @NamedQuery(name = "findActiveSessionByUser", query = "select s from Session s inner join s.credential c where c.name =:username and s.expiry > CURRENT_TIMESTAMP and s.deleted = false"),
        @NamedQuery(name = "findActiveSessions", query = "from Session s where s.deleted = false"),
        @NamedQuery(name = "findExpiredSessions", query = "from Session s where s.expiry <= CURRENT_TIMESTAMP and s.deleted = false"),
        @NamedQuery(name = "deleteActiveSessions", query = "update Session s set s.deleted = true where s.deleted = false"),
        @NamedQuery(name = "deleteExpiredSessions", query = "update Session s set s.deleted = true where s.expiry <= CURRENT_TIMESTAMP") })
public class Session extends BaseEntity {

    private static final long serialVersionUID = 4041171065363458266L;

    @ManyToOne(fetch = FetchType.EAGER)
    private Credential credential;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    public Credential getCredential() {
        return this.credential;
    }

    public Date getExpiry() {
        return this.expiry;
    }

    public Date getStart() {
        return this.start;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setStart(Date start) {
        this.start = start;
    }

}
