package framework.api.resources.authentication;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.resources.BaseResource;
import framework.api.resources.SuccessResponse;
import framework.core.domain.role.Role;
import framework.core.domain.session.Session;
import framework.core.domain.user.UserService;

@Named
@Path("/authentication")
public class AuthenticationResource extends BaseResource {

    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @Inject
    protected AuthenticationResource(UserService userService) {
        this.userService = userService;
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public LoginResponse login(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(session);
    }
    
    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value={Role.USERS})
    public SuccessResponse logout() {
        this.userService.logout(getCredential());
        return new SuccessResponse();
    }
    
    @GET
    @Path("/verify")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean verify() {
        return (this.getCredential() != null);
    }

}