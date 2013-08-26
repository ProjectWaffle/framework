package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents the user's detailed information.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "USERDETAILS")
public class Userdetails extends AbstractEntity {

    private static final long serialVersionUID = 5342989782721883971L;

    @Column
    private String address;

    @Column
    private Long birthdate;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String emailaddress;

    @Column
    private String fullname;

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
    public Long getBirthdate() {
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

}
