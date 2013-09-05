package framework.api.webservices.configuration;

import java.io.Serializable;

public class ConfigurationRequest implements Serializable {

    private static final long serialVersionUID = -4336135614922988078L;

    private String code;

    private String value;
    
    private String display;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
