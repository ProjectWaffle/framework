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
import framework.api.response.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.entity.Session;
import framework.core.entity.User;
import framework.core.service.SessionService;
import framework.core.service.UserService;
import framework.core.utilities.Cryptography;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    private Cryptography cryptography;

    private SessionService sessionService;

    private UserService userService;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        final Map<String, Object> map = this.generateRequestMap(request);
        request.setSecurityContext(new SecurityContext((User) map.get("user"), (Session) map.get("session")));
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        final Map<String, Object> map = this.generateRequestMap(request);
        final Object entity = response.getEntity();
        if (entity != null) {
            if (!(entity instanceof ServiceResponse)) {
                final String token = this.sessionService.saveOrUpdate((User) map.get("user"));
                response.setEntity(ServiceResponse.result(entity).status(ApplicationStatus.SUCCESS).token(token).build());
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
        String tokenHeader = request.getHeaderValue("token");
        String sessionid = "", username = "";
        if (tokenHeader != null) {
            tokenHeader = this.cryptography.decrypt(tokenHeader);
            sessionid = (tokenHeader.split(";")[0]).split("=")[1];
            username = (tokenHeader.split(";")[1]).split("=")[1];
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
