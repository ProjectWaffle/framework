package framework.api.webservices.authentication;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 7234813189878710433L;

    private String password;

    private String username;

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
