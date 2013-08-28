package framework.support.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import framework.api.providers.SecurityContext;
import framework.api.webservices.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;
import framework.core.domain.user.User;
import framework.core.domain.user.UserService;
import framework.core.utilities.Cryptography;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    private Cryptography cryptography;

    private SessionService sessionService;

    private UserService userService;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        final Map<String, Object> map = this.generateRequestMap(request);
        final User user = (User) map.get("user");
        final Session session = (Session) map.get("session");
        final SecurityContext securityContext = new SecurityContext(this.sessionService, user, session);
        request.setSecurityContext(securityContext);
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        final Object entity = response.getEntity();
        if (entity != null) {
            if (!(entity instanceof ServiceResponse)) {
                final String token = request.getHeaderValue("token");
                response.setEntity(ServiceResponse.result(entity).status(ApplicationStatus.SUCCESS).token(token)
                        .build());
            }
        }
        return response;
    }

    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    @Override
    public ContainerResponseFilter getResponseFilter() {
        return this;
    }

    private Map<String, Object> generateRequestMap(ContainerRequest request) {
        final Map<String, Object> map = new HashMap<String, Object>();
        String token = request.getHeaderValue("token");
        String sessionid = "", username = "";
        if (token != null) {
            token = this.cryptography.decrypt(token);
            sessionid = (token.split(";")[0]).split("=")[1];
            username = (token.split(";")[1]).split("=")[1];
            map.put("session", this.sessionService.findSessionById(username, sessionid));
            map.put("user", this.userService.findUserByUsername(username));
        }
        return map;
    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Inject
    protected void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}
