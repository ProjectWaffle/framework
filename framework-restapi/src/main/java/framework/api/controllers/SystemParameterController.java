package framework.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.request.SystemParameterRequest;
import framework.api.response.SystemParameterResponse;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;

@Named
@Path("/systemParameter")
public class SystemParameterController extends AbstractController {

    private static final long serialVersionUID = 200605594031531073L;
    private SystemParameterService systemParameterService;

    @POST
    @RolesAllowed(value = { Role.ADMINISTRATOR })
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SystemParameterResponse> loadSystemParameters(SystemParameterRequest serviceRequest) {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        final List<SystemParameter> systemParameters = this.systemParameterService.findAllActiveSystemParam();
        for (final SystemParameter systemParameter : systemParameters) {
            final SystemParameterResponse systemParameterDTO = new SystemParameterResponse();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setReadonly(String.valueOf(systemParameter.isReadonly()));
            systemParameterDTO.setValue(systemParameter.getValue());
            list.add(systemParameterDTO);
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
