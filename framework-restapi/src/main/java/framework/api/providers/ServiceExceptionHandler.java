package framework.api.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.dto.response.ServiceResponse;
import framework.core.exceptions.ApplicationException;

@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ApplicationException> {

    private final static Logger logger = Logger.getLogger(ServiceExceptionHandler.class.getName());

    @Override
    public Response toResponse(ApplicationException exception) {
        logger.log(Level.WARNING, "Error encountered during service call.", exception);

        return Response.status(Status.BAD_REQUEST)
                .entity(ServiceResponse.result().status(exception.getStatus()).build())
                .type(MediaType.APPLICATION_JSON).build();
    }

}
