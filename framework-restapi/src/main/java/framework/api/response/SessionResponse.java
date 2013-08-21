package framework.api.response;

import java.io.Serializable;

public class SessionResponse implements Serializable {

    private static final long serialVersionUID = -7717653586881038941L;

    private String sessionid;

    private String usergroupid;

    private String username;

    public String getSessionid() {
        return this.sessionid;
    }

    public String getUsergroupid() {
        return this.usergroupid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public void setUsergroupid(String usergroupid) {
        this.usergroupid = usergroupid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
