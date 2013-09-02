package framework.api.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.webservices.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.constants.EventType;
import framework.core.domain.auditlog.Auditlog;
import framework.core.domain.auditlog.AuditlogService;

@Provider
@Named
public class UnhandledExceptionMapper implements ExceptionMapper<Exception> {

    private final static Logger logger = Logger.getLogger(UnhandledExceptionMapper.class.getName());
    private final AuditlogService auditlogService;

    @Inject
    protected UnhandledExceptionMapper(AuditlogService auditlogService) {
        this.auditlogService = auditlogService;
    }

    @Override
    public Response toResponse(Exception exception) {
        logger.log(Level.WARNING, "Unhandled exception.", exception);

        final Auditlog auditlog = new Auditlog();
        auditlog.setType(EventType.EXCEPTION);
        auditlog.setDetail(String.format("%s - %s", exception.getClass().getName(), exception.getMessage()));
        this.auditlogService.saveOrUpdate(auditlog);
        return Response.serverError().type(MediaType.APPLICATION_JSON)
                .entity(ServiceResponse.result().status(ApplicationStatus.SYSTEM_EXCEPTION).build()).build();
    }

}
