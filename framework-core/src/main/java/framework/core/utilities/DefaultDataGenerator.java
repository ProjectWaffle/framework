package framework.core.utilities;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.entity.Usergroup;
import framework.core.service.ClientService;
import framework.core.service.RoleService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;
import framework.core.service.UsergroupService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private ClientService clientService;
    private RoleService roleService;
    private SystemParameterService systemParameterService;
    private UsergroupService usergroupService;
    private UserService userService;

    @Override
    protected Integer getDBVersion() {
        return 1;
    }

    @Override
    protected void performDataOperation() {
        Client client = this.retrieveXMLContent("DefaultClient.data", Client.class);
        client = this.clientService.saveOrUpdate(client);

        List<SystemParameter> systemParameters = this.retrieveXMLContent("DefaultSystemParameters.data", SystemParameter.class);
        for (final SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(this.getCryptography().encrypt(systemParameter.getValue()));
            systemParameter.setClient(client);
        }
        systemParameters = this.systemParameterService.saveOrUpdate(systemParameters);

        List<Role> roles = this.retrieveXMLContent("DefaultRoles.data", Role.class);
        roles = this.roleService.saveOrUpdate(roles);

        Usergroup usergroup = this.retrieveXMLContent("DefaultGroup.data", Usergroup.class, Role.class);
        usergroup.setRoles(roles);
        usergroup.setClients(Arrays.asList(client));
        usergroup = this.usergroupService.saveOrUpdate(usergroup);

        User user = (User) this.retrieveXMLContent("DefaultUser.data", User.class, Usergroup.class, Role.class);
        user.setPassword(this.getCryptography().encrypt(user.getPassword()));
        user.setClient(client);
        user.setUsergroup(usergroup);
        user = this.userService.saveOrUpdate(user);
    }

    @Inject
    protected void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Inject
    protected void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

    @Inject
    protected void setUsergroupService(UsergroupService usergroupService) {
        this.usergroupService = usergroupService;
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}
