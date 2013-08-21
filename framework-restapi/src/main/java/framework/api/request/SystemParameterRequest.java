package framework.api.request;

import java.io.Serializable;

public class SystemParameterRequest implements Serializable {

    private static final long serialVersionUID = -3505481776642593651L;

    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
