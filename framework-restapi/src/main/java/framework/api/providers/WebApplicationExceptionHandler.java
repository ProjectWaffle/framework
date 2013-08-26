package framework.api.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.response.ServiceResponse;
import framework.core.constants.ApplicationStatus;

@Provider
public class WebApplicationExceptionHandler implements ExceptionMapper<WebApplicationException> {

    private final static Logger logger = Logger.getLogger(WebApplicationExceptionHandler.class.getName());

    @Override
    public Response toResponse(WebApplicationException exception) {
        logger.log(Level.WARNING, "Error encountered during HTTP response.", exception);
        switch (exception.getResponse().getStatus()) {
            case 403:
                return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_JSON)
                        .entity(ServiceResponse.result().status(ApplicationStatus.FORBIDDEN).build()).build();
            case 404:
                return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON)
                        .entity(ServiceResponse.result().status(ApplicationStatus.SERVICE_NOT_FOUND).build()).build();
            default:
                return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
                        .entity(ServiceResponse.result().status(ApplicationStatus.SYSTEM_EXCEPTION).build()).build();
        }
    }

}
