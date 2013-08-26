package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SESSION")
@NamedQueries(value = {
        @NamedQuery(name = "findSessionById", query = "from Session where id =:id"),
        @NamedQuery(name = "findSessionByUser", query = "select s from Session s inner join s.user u where u.name =:username"),
        @NamedQuery(name = "findExpiredSessions", query = "from Session where expiry <= :expiry") })
public class Session extends AbstractEntity {

    private static final long serialVersionUID = 4041171065363458266L;

    @Column
    private Long expiry;

    @Column
    private Long start;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Long getExpiry() {
        return this.expiry;
    }

    public Long getStart() {
        return this.start;
    }

    public User getUser() {
        return this.user;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
