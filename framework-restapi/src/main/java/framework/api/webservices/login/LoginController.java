package framework.api.webservices.login;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.BaseController;
import framework.api.webservices.SuccessResponse;
import framework.core.domain.role.Role;
import framework.core.domain.session.Session;
import framework.core.domain.user.UserService;

@Named
@Path("/login")
public class LoginController extends BaseController {

    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public LoginResponse login(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(session);
    }
    
    @GET
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value={Role.ADMINISTRATORS, Role.USERS})
    public SuccessResponse logout() {
        this.userService.logout(getCredential());
        return new SuccessResponse();
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}