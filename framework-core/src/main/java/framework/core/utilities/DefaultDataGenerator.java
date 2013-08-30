package framework.core.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.constants.ParameterType;
import framework.core.domain.client.Client;
import framework.core.domain.client.ClientService;
import framework.core.domain.role.Role;
import framework.core.domain.role.RoleService;
import framework.core.domain.systemparameter.SystemParameter;
import framework.core.domain.systemparameter.SystemParameterService;
import framework.core.domain.user.User;
import framework.core.domain.user.UserService;
import framework.core.domain.userdetails.Userdetails;
import framework.core.domain.usergroup.Usergroup;
import framework.core.domain.usergroup.UsergroupService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private ClientService clientService;
    private RoleService roleService;
    private SystemParameterService systemParameterService;
    private UsergroupService usergroupService;
    private UserService userService;

    private Client generateClient() {
        final Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, 15);

        final Client client = new Client();
        client.setName("DEFAULT");
        client.setValidity(now.getTime());
        client.setEmailaddress("default@domain.com");
        client.setMobile("000-000-000");
        client.setPhone("000-000-000");
        return this.clientService.saveOrUpdate(client);
    }

    private List<Role> generateRoles() {
        final List<Role> roles = new ArrayList<Role>();

        Role role = new Role();
        role.setName(Role.ADMINISTRATORS);
        role.setDescription("System Administrator");
        roles.add(role);

        role = new Role();
        role.setName(Role.USERS);
        role.setDescription("Users");
        roles.add(role);

        return this.roleService.saveOrUpdate(roles);
    }

    private List<SystemParameter> generateSystemParameters(Client client) {
        final List<SystemParameter> systemParameters = new ArrayList<SystemParameter>();

        SystemParameter systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.EMAIL_ADDRESS);
        systemParameter.setDescription("label.description");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.EMAIL);
        systemParameter.setValue(this.getCryptography().encrypt("frederickeyap@gmail.com"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.EMAIL_HOST);
        systemParameter.setDescription("label.email_host");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.STRING);
        systemParameter.setValue(this.getCryptography().encrypt("pop3@gmail.com"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.EMAIL_HOST);
        systemParameter.setDescription("label.email_password");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.PASSWORD);
        systemParameter.setValue(this.getCryptography().encrypt("password1234"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.ANONYMOUS_USER_CREATION);
        systemParameter.setDescription("label.anonymous_user_creation");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.BOOLEAN);
        systemParameter.setValue(this.getCryptography().encrypt("false"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.SMTP_AUTHENTICATION);
        systemParameter.setDescription("label.smtp_authentication");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.BOOLEAN);
        systemParameter.setValue(this.getCryptography().encrypt("true"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.SMTP_HOST);
        systemParameter.setDescription("label.smtp_host");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.STRING);
        systemParameter.setValue(this.getCryptography().encrypt("smtp.gmail.com"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(256));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        systemParameter = new SystemParameter();
        systemParameter.setCode(ParameterCode.SESSION_TIMEOUT);
        systemParameter.setDescription("label.session_timeout");
        systemParameter.setReadonly(false);
        systemParameter.setType(ParameterType.STRING);
        systemParameter.setValue(this.getCryptography().encrypt("5"));
        systemParameter.setMinimum(Long.valueOf(0));
        systemParameter.setMaximum(Long.valueOf(600));
        systemParameter.setClient(client);
        systemParameters.add(systemParameter);

        return this.systemParameterService.saveOrUpdate(systemParameters);
    }

    private User generateUser(Usergroup usergroup, Client client) {
        final Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, 15);

        final Userdetails userdetails = new Userdetails();
        userdetails.setFullname("Project Waffle");
        userdetails.setCity("Manila");
        userdetails.setCountry("Philippines");
        userdetails.setEmailaddress("pwaffle@gmail.com");

        final User user = new User();
        user.setUsergroup(usergroup);
        user.setClient(client);
        user.setUserdetails(userdetails);
        user.setName("administrator");
        user.setPassword(this.getCryptography().encrypt("password1234"));
        user.setPasswordexpiration(now.getTime());
        user.setProfileexpiration(now.getTime());
        user.setLocale("EN_US");
        return this.userService.saveOrUpdate(user);
    }

    private Usergroup generateUsergroup(List<Role> roles, List<Client> clients) {
        final Usergroup usergroup = new Usergroup();
        usergroup.setName("SYS_ADMIN");
        usergroup.setRoles(roles);
        usergroup.setClients(clients);
        usergroup.setDescription("System Administrators");
        return this.usergroupService.saveOrUpdate(usergroup);
    }

    @Override
    protected Integer getDBVersion() {
        return 1;
    }

    @Override
    protected void performDataOperation() {
        final Client client = this.generateClient();

        final List<Role> roles = this.generateRoles();

        final Usergroup usergroup = this.generateUsergroup(roles, Arrays.asList(client));

        this.generateSystemParameters(client);

        this.generateUser(usergroup, client);
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
