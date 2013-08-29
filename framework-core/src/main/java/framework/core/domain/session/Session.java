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
import framework.core.domain.user.User;

@Entity
@Table(name = "SESSION")
@NamedQueries(value = {
        @NamedQuery(name = "findActiveSessionById", query = "from Session s where s.id =:id and s.deleted = false"),
        @NamedQuery(name = "findActiveSessionByUser", query = "select s from Session s inner join s.user u where u.name =:username and s.expiry > CURRENT_TIMESTAMP and s.deleted = false"),
        @NamedQuery(name = "findExpiredSessions", query = "from Session where expiry <= CURRENT_TIMESTAMP") })
public class Session extends BaseEntity {

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
