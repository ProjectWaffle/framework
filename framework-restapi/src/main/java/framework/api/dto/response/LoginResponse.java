package framework.api.dto.response;

import framework.core.domain.model.Session;

public class LoginResponse {

    private final String sessionid;

    private final String username;

    public LoginResponse(Session session) {
        this.sessionid = session.getId();
        this.username = session.getCredential().getName();
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public String getUsername() {
        return username;
    }

}
