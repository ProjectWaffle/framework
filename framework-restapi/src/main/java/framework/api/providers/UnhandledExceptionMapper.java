package framework.api.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.resources.ServiceResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.domain.auditlog.AuditlogService;

@Provider
@Named
public class UnhandledExceptionMapper implements ExceptionMapper<Exception> {

    private final static Logger logger = Logger.getLogger(UnhandledExceptionMapper.class.getName());
    private AuditlogService auditlogService;
    
    @Inject
    protected UnhandledExceptionMapper(AuditlogService auditlogService) {
        this.auditlogService = auditlogService;
    }
    
    @Override
    public Response toResponse(Exception exception) {
        logger.log(Level.WARNING, "Unhandled exception.", exception);

        this.auditlogService.saveOrUpdate(exception);
        return Response.serverError().type(MediaType.APPLICATION_JSON)
                .entity(ServiceResponse.result().status(ApplicationStatus.SYSTEM_EXCEPTION).build()).build();
    }

}
