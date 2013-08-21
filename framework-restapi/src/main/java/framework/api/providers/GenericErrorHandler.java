package framework.api.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.response.ServiceResponse;
import framework.core.exceptions.ServiceException;

@Provider
public class GenericErrorHandler implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
        return Response.status(Status.BAD_REQUEST)
                .entity(ServiceResponse.result().status(exception.getStatus()).build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
