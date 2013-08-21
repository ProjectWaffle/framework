package framework.api.response;

import java.io.Serializable;

public class SystemParameterResponse implements Serializable {

    private static final long serialVersionUID = 908256462946326444L;

    private String code;

    private String description;

    private String maximum;

    private String minimum;

    private String readonly;

    private String type;

    private String value;

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMaximum() {
        return this.maximum;
    }

    public String getMinimum() {
        return this.minimum;
    }

    public String getReadonly() {
        return this.readonly;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
