package framework.api.response;

import java.io.Serializable;

import framework.core.entity.SystemParameter;

public class SystemParameterResponse implements Serializable {

    private static final long serialVersionUID = 908256462946326444L;

    private final String code;

    private final String description;

    private final String maximum;

    private final String minimum;

    private final String readonly;

    private final String type;

    private final String value;

    public SystemParameterResponse(SystemParameter systemParameter) {
        this.code = systemParameter.getCode().name();
        this.description = systemParameter.getDescription();
        this.maximum = String.valueOf(systemParameter.getMaximum());
        this.minimum = String.valueOf(systemParameter.getMinimum());
        this.type = systemParameter.getType().name();
        this.readonly = String.valueOf(systemParameter.isReadonly());
        this.value = systemParameter.getValue();
    }

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

}
