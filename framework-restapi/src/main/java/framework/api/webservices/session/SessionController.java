package framework.api.webservices.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.core.domain.role.Role;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;

@Named
@Path("/session")
public class SessionController {

    private SessionService sessionService;
    
    @Inject
    protected SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public List<SessionResponse> loadActiveSession() {
        List<SessionResponse> sessionResponses = new ArrayList<SessionResponse>();
        for (Session session : this.sessionService.findActiveSessions()) {
            sessionResponses.add(new SessionResponse(session));
        }
        return sessionResponses;
    }
    
}
