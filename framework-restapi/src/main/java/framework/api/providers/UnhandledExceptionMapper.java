package framework.api.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.response.ServiceResponse;
import framework.core.enums.ApplicationStatus;

@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.serverError()
                .entity(ServiceResponse.result().status(ApplicationStatus.SYSTEM_EXCEPTION).build()).build();
    }

}
