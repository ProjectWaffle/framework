package framework.api.resource.configuration;

import java.io.Serializable;

import framework.core.domain.configuration.Configuration;

public class ConfigurationResponse implements Serializable {

    private static final long serialVersionUID = 908256462946326444L;

    private String code;

    private String display;

    private String value;

    public ConfigurationResponse(Configuration configuration) {
        this.code = configuration.getReference().getCode();
        this.display = configuration.getReference().getLabel();
        this.value = configuration.getValue();
    }

    public String getCode() {
        return this.code;
    }

    public String getDisplay() {
        return this.display;
    }

    public String getValue() {
        return this.value;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
