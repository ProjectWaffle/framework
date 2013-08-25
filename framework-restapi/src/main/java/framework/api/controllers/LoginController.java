package framework.api.controllers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.request.LoginRequest;
import framework.api.response.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.service.UserService;

@Named
@Path("/login")
public class LoginController extends AbstractController {

    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public ServiceResponse<?> processRequest(LoginRequest loginRequest) {
        final String token = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ServiceResponse.result().status(ApplicationStatus.SUCCESS).token(token).build();
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}