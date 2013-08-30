package framework.core.domain.userdetails;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;

/**
 * Represents the user's detailed information.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "USERDETAILS")
public class Userdetails extends BaseEntity {

    private static final long serialVersionUID = 5342989782721883971L;

    @Column
    private String address;

    @Column
    private Date birthdate;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String emailaddress;

    @Column
    private String fullname;

    @Column
    private String locale;

    @Column
    private String phone;

    @Column
    private Long zipcode;

    /**
     * Returns the user's address.
     * 
     * @return the user's address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Returns the user's birthdate.
     * 
     * @return the user's birthdate.
     */
    public Date getBirthdate() {
        return this.birthdate;
    }

    /**
     * Returns the user's city.
     * 
     * @return the user's city.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Returns the user's country.
     * 
     * @return the user's country.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Returns the user's e-mail address.
     * 
     * @return the user's e-mail address.
     */
    public String getEmailaddress() {
        return this.emailaddress;
    }

    /**
     * Returns the user's fullname.
     * 
     * @return the user's fullname.
     */
    public String getFullname() {
        return this.fullname;
    }

    public String getLocale() {
        return this.locale;
    }

    /**
     * Returns the user's phone number.
     * 
     * @return the user's phone number.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Returns the user's zip code.
     * 
     * @return the user's zip code.
     */
    public Long getZipcode() {
        return this.zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

}
