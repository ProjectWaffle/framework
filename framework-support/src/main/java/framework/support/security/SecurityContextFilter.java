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

import framework.api.dto.response.ServiceResponse;
import framework.api.providers.SecurityContext;
import framework.core.domain.model.Credential;
import framework.core.domain.model.Session;
import framework.core.domain.service.AuthenticationService;
import framework.core.exceptions.ApplicationStatus;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    private final AuthenticationService authenticationService;

    private final AuthenticationService userService;

    @Inject
    protected SecurityContextFilter(AuthenticationService authenticationService, AuthenticationService userService) {
        super();
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        final Map<String, Cookie> cookie = request.getCookies();
        Credential user = null;
        Session session = null;
        if (cookie.containsKey("sessionid") && cookie.containsKey("username")) {
            session = this.authenticationService.findSessionById(
                    cookie.get("username").getValue(), 
                    cookie.get("sessionid").getValue());
            if (session != null) {
                user = this.userService.findCredentialByUsername(cookie.get("username").getValue());
            }
        }
        final SecurityContext securityContext = new SecurityContext(this.authenticationService, user, session);
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
