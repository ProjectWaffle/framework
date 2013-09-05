package framework.api.providers;

import java.security.Principal;

import framework.core.domain.role.Role;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;
import framework.core.domain.user.Credential;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    private final SessionService service;
    private final Session session;
    private final Credential user;

    public SecurityContext(SessionService service, Credential user, Session session) {
        this.user = user;
        this.session = session;
        this.service = service;
    }

    @Override
    public String getAuthenticationScheme() {
        return javax.ws.rs.core.SecurityContext.CLIENT_CERT_AUTH;
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
                this.service.saveOrUpdate(this.user);
                return true;
            }
        }
        return false;
    }

}
