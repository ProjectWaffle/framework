package framework.core.domain.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;
import framework.core.domain.client.Client;
import framework.core.domain.reference.Reference;

@Entity
@Table(name = "CONFIGURATION")
public class Configuration extends BaseEntity {

    private static final long serialVersionUID = -6161991656266437823L;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Reference reference;

    @Column(nullable = false)
    private String value;

    public Client getClient() {
        return this.client;
    }

    public Reference getReference() {
        return this.reference;
    }

    /**
     * @return
     */
    public String getValue() {
        return this.value;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public void setValue(boolean value) {
        this.value = String.valueOf(value);
    }

    public void setValue(int value) {
        this.value = String.valueOf(value);
    }

    public void setValue(long value) {
        this.value = String.valueOf(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

}
