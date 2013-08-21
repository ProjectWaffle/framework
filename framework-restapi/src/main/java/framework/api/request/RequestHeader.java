package framework.api.request;

import java.io.Serializable;

public class RequestHeader implements Serializable {

    private static final long serialVersionUID = 5972916684946587287L;

    private String sessionid;

    private String userid;

    public String getSessionid() {
        return this.sessionid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
