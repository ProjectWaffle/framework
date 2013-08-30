package framework.api.webservices.login;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.BaseController;
import framework.core.domain.session.Session;
import framework.core.domain.user.UserService;

@Named
@Path("/login")
public class LoginController extends BaseController {

    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public LoginResponse processRequest(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(session);
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}