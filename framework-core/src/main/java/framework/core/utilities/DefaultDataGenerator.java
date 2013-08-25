package framework.core.utilities;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.entity.Usergroup;
import framework.core.service.ClientService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private final ClientService clientService;
    private final SystemParameterService systemParameterService;
    private final UserService userService;

    @Inject
    public DefaultDataGenerator(UserService userService, ClientService clientService, SystemParameterService systemParameterService) {
        this.userService = userService;
        this.clientService = clientService;
        this.systemParameterService = systemParameterService;
    }

    @Override
    protected Integer getDBVersion() {
        return 1;
    }

    @Override
    protected void performDataOperation() {
        final List<SystemParameter> systemParameters = this.retrieveXMLContent("DefaultSystemParameters.data", SystemParameter.class);
        final User user = (User) this.retrieveXMLContent("DefaultUser.data", User.class, Usergroup.class, Role.class);
        final Client client = this.clientService.saveOrUpdate((Client) this.retrieveXMLContent("DefaultClient.data", Client.class));

        for (final SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(this.getCryptography().encrypt(systemParameter.getValue()));
            systemParameter.setClient(client);
        }
        this.systemParameterService.saveOrUpdate(systemParameters);

        user.setPassword(this.getCryptography().encrypt(user.getPassword()));
        user.setClient(client);
        user.getUsergroup().getClients().add(client);
        this.userService.saveOrUpdate(user);
    }

}
