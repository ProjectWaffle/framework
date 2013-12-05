package framework.api.resource.session;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import framework.core.domain.session.Session;

public class SessionResponse implements Serializable {

    private static final long serialVersionUID = 4362445606610861025L;

    private String username;
    private String usergroup;
    private String start;
    private String expiry;
    
    public SessionResponse(Session session) {
        this.username = session.getCredential().getName();
        this.usergroup = session.getCredential().getUsergroup().getName();
        this.start = new SimpleDateFormat("MM/dd/yyyy").format(session.getStart());
        this.expiry = new SimpleDateFormat("MM/dd/yyyy").format(session.getExpiry());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getUsername() {
        return username;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public String getStart() {
        return start;
    }

    public String getExpiry() {
        return expiry;
    }
}
