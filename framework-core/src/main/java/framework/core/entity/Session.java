package framework.core.entity;

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

@Entity
@Table(name = "SESSION")
@NamedQueries(value = {
        @NamedQuery(name = "findSessionById", query = "from Session where id =:id"),
        @NamedQuery(name = "findSessionByUser", query = "select s from Session s inner join s.user u where u.name =:username"),
        @NamedQuery(name = "findExpiredSessions", query = "from Session where expiry <= :expiry") })
public class Session extends AbstractEntity {

    private static final long serialVersionUID = 4041171065363458266L;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Date getExpiry() {
        return this.expiry;
    }

    public Date getStart() {
        return this.start;
    }

    public User getUser() {
        return this.user;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
