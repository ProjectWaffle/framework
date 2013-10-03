package framework.support.security;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Cookie;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import framework.api.providers.SecurityContext;
import framework.api.resources.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;
import framework.core.domain.user.Credential;
import framework.core.domain.user.UserService;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    private final SessionService sessionService;

    private final UserService userService;

    @Inject
    protected SecurityContextFilter(SessionService sessionService, UserService userService) {
        super();
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        final Map<String, Cookie> cookie = request.getCookies();
        Credential user = null;
        Session session = null;
        if (cookie.containsKey("sessionid") && cookie.containsKey("username")) {
            session = this.sessionService.findSessionById(
                    cookie.get("username").getValue(), 
                    cookie.get("sessionid").getValue());
            if (session != null) {
                user = this.userService.findCredentialByUsername(cookie.get("username").getValue());
            }
        }
        final SecurityContext securityContext = new SecurityContext(this.sessionService, user, session);
        request.setSecurityContext(securityContext);
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        final Object entity = response.getEntity();
        if (entity != null) {
            if (!(entity instanceof ServiceResponse)) {
                response.setEntity(ServiceResponse.result(entity).status(ApplicationStatus.SUCCESS).build());
            }
        } else {
            response.setEntity(ServiceResponse.result(entity).status(ApplicationStatus.NO_RECORDS_FOUND).build());
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

}
