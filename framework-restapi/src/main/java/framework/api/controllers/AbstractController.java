package framework.api.controllers;

import java.io.Serializable;

import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import framework.core.entity.User;

@Named
public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = -1508227485108273495L;

    @Context
    private SecurityContext securityContext;

    protected AbstractController() {
    }

    protected User getAuthenticatedUser() {
        final User user = (User) this.securityContext.getUserPrincipal();
        return user;
    }
    
}
