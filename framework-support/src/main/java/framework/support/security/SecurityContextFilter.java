package framework.support.security;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import framework.api.providers.SecurityContext;
import framework.api.request.ServiceRequest;
import framework.api.response.ServiceResponse;
import framework.core.entity.Session;
import framework.core.entity.User;
import framework.core.enums.ApplicationStatus;
import framework.core.service.SessionService;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private SessionService sessionService;
    
    @Override
    public ContainerRequest filter(ContainerRequest request) {
        User user = null;
        Session session = null;
        String sessionid = null;
        Long userid = null;
        final ServiceRequest<?> serviceRequest = request.getEntity(ServiceRequest.class);
        if (serviceRequest.getRequestHeader() != null) {
            sessionid = serviceRequest.getRequestHeader().getSessionid();
            userid = Long.valueOf(serviceRequest.getRequestHeader().getUserid());
            session = this.sessionService.extendSession(userid, sessionid);
            if (session != null) {
                user = session.getUser();
            }
        }
        request.setEntity(Object.class, null, null, MediaType.APPLICATION_JSON_TYPE, null, serviceRequest.getRequest());
        request.setSecurityContext(new SecurityContext(user, session));
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        final Object entity = response.getEntity();
        if (entity != null) {
            response.setEntity(ServiceResponse.result(entity).status(ApplicationStatus.SUCCESS).build());
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
