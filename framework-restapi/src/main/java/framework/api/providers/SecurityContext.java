package framework.api.providers;

import java.security.Principal;

import framework.core.domain.model.Credential;
import framework.core.domain.model.Role;
import framework.core.domain.model.Session;
import framework.core.domain.service.AuthenticationService;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    private final AuthenticationService service;
    private final Session session;
    private final Credential user;

    public SecurityContext(AuthenticationService service, Credential user, Session session) {
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
        if (Role.USER.equals(name)) {
            return true;
        }
        for (final String role : this.user.getUsergroup().getRoles()) {
            if (role.equals(name)) {
                this.service.saveOrUpdate(this.user);
                return true;
            }
        }
        return false;
    }

}
