package framework.api.controllers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.request.LoginRequest;
import framework.api.response.SessionResponse;
import framework.core.entity.Session;
import framework.core.service.UserService;

@Named
@Path("/login")
public class LoginController extends AbstractController {

    /**
     * 
     */
    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public SessionResponse processRequest(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setSessionId(String.valueOf(session.getId()));
        sessionResponse.setUserId(String.valueOf(session.getUser().getId()));
        sessionResponse.setUsergroupId(String.valueOf(session.getUser().getUsergroup().getId()));
        return sessionResponse;
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}