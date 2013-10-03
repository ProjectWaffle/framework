package framework.core.domain.session;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import framework.core.domain.BaseEntity;
import framework.core.domain.auditlog.Auditable;
import framework.core.domain.user.Credential;

@Entity
@Table(name = "SESSION")
public class Session extends BaseEntity {

    private static final long serialVersionUID = 4041171065363458266L;

    @ManyToOne(fetch = FetchType.EAGER)
    @Auditable
    private Credential credential;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Auditable
    private Date expiry;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Auditable
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
