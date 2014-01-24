package framework.core.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CLIENT")
public class Client extends BaseEntity {

    private static final long serialVersionUID = 2800740052878163935L;

    @Column(nullable = false, unique = true)
    @Auditable
    private String emailaddress;

    @Column(nullable = false, unique = true)
    @Auditable
    private String mobile;

    @Column(nullable = false, unique = true)
    @Auditable
    private String name;

    @Column(nullable = false, unique = true)
    @Auditable
    private String phone;

    @Column(nullable = false, unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Auditable
    private Date validity;

    public String getEmailaddress() {
        return this.emailaddress;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public Date getValidity() {
        return this.validity;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

}
