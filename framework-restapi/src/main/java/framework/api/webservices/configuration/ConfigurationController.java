package framework.api.webservices.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.BaseController;
import framework.core.domain.configuration.Configuration;
import framework.core.domain.configuration.ConfigurationService;
import framework.core.domain.role.Role;

@Named
@Path("/configuration")
public class ConfigurationController extends BaseController {

    private static final long serialVersionUID = 200605594031531073L;
    private final ConfigurationService configurationService;

    @Inject
    protected ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public List<ConfigurationResponse> loadConfiguration() {
        final List<ConfigurationResponse> list = new ArrayList<ConfigurationResponse>();
        final List<Configuration> configurations = this.configurationService.findAllActiveConfiguration(this.getAuthenticatedUser());
        for (final Configuration configuration : configurations) {
            final ConfigurationResponse systemParameterResponse = new ConfigurationResponse(configuration);
            systemParameterResponse.setDisplay(this.getMessage(systemParameterResponse.getDisplay()));
            list.add(systemParameterResponse);
        }
        return list;
    }

    @GET
    @Path(value = "/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public ConfigurationResponse loadSystemParameters(@PathParam(value = "code") String code) {
        final String clientName = this.getAuthenticatedUser().getClient().getName();
        final Configuration configuration = this.configurationService.findConfigurationByRefCodeAndClient(code, clientName);
        final ConfigurationResponse configurationResponse = new ConfigurationResponse(configuration);
        configurationResponse.setDisplay(this.getMessage(configurationResponse.getDisplay()));
        return configurationResponse;
    }

}
