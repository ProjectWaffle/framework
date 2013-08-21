package framework.api.providers;

import java.security.Principal;

import framework.core.entity.Role;
import framework.core.entity.Session;
import framework.core.entity.User;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    private final Session session;
    private final User user;

    public SecurityContext(User user, Session session) {
        this.user = user;
        this.session = session;
    }

    @Override
    public String getAuthenticationScheme() {
        return javax.ws.rs.core.SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public boolean isUserInRole(String name) {
        if ((null == this.session) || (null == this.user)) {
            return false;
        }
        for (final Role role : this.user.getUsergroup().getRoles()) {
            if (role.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
