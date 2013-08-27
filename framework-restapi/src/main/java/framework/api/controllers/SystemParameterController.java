package framework.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.response.SystemParameterResponse;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;

@Named
@Path("/systemParameter")
public class SystemParameterController extends AbstractController {

    private static final long serialVersionUID = 200605594031531073L;
    private SystemParameterService systemParameterService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public List<SystemParameterResponse> loadSystemParameters() {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        final String clientName = getAuthenticatedUser().getClient().getName();
        final List<SystemParameter> systemParameters = this.systemParameterService.findAllActiveSystemParam(clientName);
        for (final SystemParameter systemParameter : systemParameters) {
            list.add(new SystemParameterResponse(systemParameter));
        }
        return list;
    }

    /**
     * @param systemParameterService
     *            the systemParameterService to set
     */
    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

}
