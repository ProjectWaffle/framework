package framework.api.request;

import java.io.Serializable;

public class RequestHeader implements Serializable {

    private static final long serialVersionUID = 5972916684946587287L;

    private String sessionid;

    private String username;

    public String getSessionid() {
        return this.sessionid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
