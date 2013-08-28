package framework.core.domain.localization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;

@Entity
@Table(name = "LOCALIZATION")
@NamedQueries({ @NamedQuery(name = "findByKeyAndLocale", query = "from Localization l where key=:key and value=:value") })
public class Localization extends BaseEntity {

    private static final long serialVersionUID = -3688082536370841611L;

    @Column
    private String key;

    @Column
    private String language;

    @Column
    private String value;

    public String getKey() {
        return this.key;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getValue() {
        return this.value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
