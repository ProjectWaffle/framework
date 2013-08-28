package framework.api.webservices.login;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.Controller;
import framework.api.webservices.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.domain.user.UserService;

@Named
@Path("/login")
public class LoginController extends Controller {

    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public ServiceResponse<String> processRequest(LoginRequest loginRequest) {
        final String token = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ServiceResponse.result(token).status(ApplicationStatus.SUCCESS).token(token).build();
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}