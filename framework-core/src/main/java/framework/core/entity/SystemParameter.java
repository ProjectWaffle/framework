package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import framework.core.enums.ParameterCode;
import framework.core.enums.ParameterType;

/**
 * The System Parameter entity is a place-value holder for environment variables used for managing the state of the
 * application. Unlike <em>system.properties</em>, such values can be changed at any given point of time while the
 * application is running.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "SYSTEMPARAMETER")
@NamedQueries(value = {
        @NamedQuery(name = "findAllActiveSystemParam", query = "from SystemParameter where deleted = false"),
        @NamedQuery(name = "findSystemParametersByCode", query = "from SystemParameter where code = :code"),
        @NamedQuery(name = "updateValue", query = "update SystemParameter set value = :value where code = :code") })
public class SystemParameter extends AbstractEntity {

    private static final long serialVersionUID = 7583893180559514999L;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ParameterCode code;

    @Column
    private String description;

    @Column
    private Long maximum;

    @Column
    private Long minimum;

    @Column
    private boolean readonly;

    @Column
    @Enumerated(EnumType.STRING)
    private ParameterType type;

    @Column
    private String value;

    /**
     * Creates a new instance of {@link SystemParameter}.
     */
    protected SystemParameter() {

    }

    /**
     * Returns the unique code of the System Parameter. This is equivalent to system environment variable name.
     * 
     * @return the unique code.
     */
    public ParameterCode getCode() {
        return this.code;
    }

    /**
     * Returns the purpose of the System Parameter.
     * 
     * @return the purpose of the System Parameter.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the maximum allowable size for the value. If the {@link ParameterType} is <em>NUMERIC</em>, this
     * translate to the maximum allowable parameter value; but if the {@link ParameterType} is <em>STRING</em> or
     * <em>EMAIL</em> this translate to the maximum characters. For instance, <em>EMAIL_HOST</em> is a <em>STRING</em>
     * and the minimum is 10, therefore the value <em>MICROSOFT.COM</em> is invalid since the number of characters is
     * more than the maximum allowed. Likewise, <em>EMAIL_HOST</em> is <em>NUMERIC</em> and the maximum is 999,
     * therefore the value <em>1000</em> is invalid since it is more than 999.
     * 
     * @return the minimum allowable size for the value.
     */
    public Long getMaximum() {
        return this.maximum;
    }

    /**
     * Returns the minimum allowable size for the value. If the {@link ParameterType} is <em>NUMERIC</em>, this
     * translate to the minimum allowable parameter value; but if the {@link ParameterType} is <em>STRING</em> or
     * <em>EMAIL</em> this translate to the minimum characters. For instance, <em>EMAIL_HOST</em> is a <em>STRING</em>
     * and the minimum is 10, therefore the value <em>GO.COM</em> is invalid since the number of characters is less than
     * the minimum allowed. Likewise, <em>EMAIL_HOST</em> is <em>NUMERIC</em> and the minimum is 100, therefore the
     * value <em>50</em> is invalid since it is less than 100.
     * 
     * @return the minimum allowable size for the value.
     */
    public Long getMinimum() {
        return this.minimum;
    }

    /**
     * Returns the type of the System Parameter. See {@link ParameterType} for specific return values.
     * 
     * @return the type of the System Parameter.
     */
    public ParameterType getType() {
        return this.type;
    }

    /**
     * Returns the value of the System Parameter. This is the actual system environment variable value.
     * 
     * @return the value of the System Parameter.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Returns true if the System Parameter value can not be modified by the user, otherwise false.
     * 
     * @return if the user can modify the System Parameter value.
     */
    public boolean isReadonly() {
        return this.readonly;
    }

    public void setCode(ParameterCode code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaximum(Long maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(Long minimum) {
        this.minimum = minimum;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    /**
     * Sets the value of the System Parameter.
     * 
     * @param value
     *            the value to set.
     */
    public void setValue(Object value) {
        this.value = String.valueOf(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

}
