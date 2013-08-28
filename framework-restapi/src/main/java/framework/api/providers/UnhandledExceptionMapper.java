package framework.api.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.webservices.ServiceResponse;
import framework.core.constants.ApplicationStatus;

@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<Exception> {

    private final static Logger logger = Logger.getLogger(UnhandledExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        logger.log(Level.WARNING, "Error encountered during HTTP response.", exception);

        return Response.serverError().type(MediaType.APPLICATION_JSON)
                .entity(ServiceResponse.result().status(ApplicationStatus.SYSTEM_EXCEPTION).build()).build();
    }

}
