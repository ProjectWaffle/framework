package framework.api.webservices;

import java.io.Serializable;

import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import framework.core.domain.user.User;

@Named
public abstract class Controller implements Serializable {

    private static final long serialVersionUID = -1508227485108273495L;

    @Context
    private SecurityContext securityContext;

    protected Controller() {
    }

    protected User getAuthenticatedUser() {
        final User user = (User) this.securityContext.getUserPrincipal();
        return user;
    }
    
}
