package framework.core.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ReferenceCode;
import framework.core.constants.ReferenceType;
import framework.core.domain.client.Client;
import framework.core.domain.client.ClientService;
import framework.core.domain.configuration.Configuration;
import framework.core.domain.configuration.ConfigurationService;
import framework.core.domain.reference.Reference;
import framework.core.domain.reference.ReferenceService;
import framework.core.domain.role.Role;
import framework.core.domain.role.RoleService;
import framework.core.domain.user.User;
import framework.core.domain.user.UserService;
import framework.core.domain.userdetails.Userdetails;
import framework.core.domain.usergroup.Usergroup;
import framework.core.domain.usergroup.UsergroupService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private final ClientService clientService;
    private final ConfigurationService configurationService;
    private final ReferenceService referenceService;
    private final RoleService roleService;
    private final UsergroupService usergroupService;
    private final UserService userService;

    @Inject
    public DefaultDataGenerator(ClientService clientService, RoleService roleService,
            ReferenceService referenceService, ConfigurationService configurationService,
            UsergroupService usergroupService, UserService userService) {
        super();
        this.clientService = clientService;
        this.roleService = roleService;
        this.referenceService = referenceService;
        this.configurationService = configurationService;
        this.usergroupService = usergroupService;
        this.userService = userService;
    }

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

    private void generateConfiguration(Client client) {
        Reference reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_EMAIL_ADDRESS);
        reference.setLabel("label.email_address");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        Configuration configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_EMAIL_HOST);
        reference.setLabel("label.email_host");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_EMAIL_PASSWORD);
        reference.setLabel("label.email_password");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_ANONYMOUS_USER_CREATION);
        reference.setLabel("label.anonymous_user_creation");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_SMTP_AUTHENTICATION);
        reference.setLabel("label.smtp_authentication");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_SMTP_HOST);
        reference.setLabel("label.smtp_host");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue("Default");
        this.configurationService.saveOrUpdate(configuration);

        reference = new Reference();
        reference.setCode(ReferenceCode.CONFIGURATION_SESSION_TIMEOUT);
        reference.setLabel("label.session_timeout");
        reference.setType(ReferenceType.SYSTEM_PARAMETERS);
        configuration = new Configuration();
        configuration.setClient(client);
        configuration.setReference(this.referenceService.saveOrUpdate(reference));
        configuration.setValue(30);
        this.configurationService.saveOrUpdate(configuration);
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

    private User generateUser(Usergroup usergroup, Client client) {
        final Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, 15);

        final Userdetails userdetails = new Userdetails();
        userdetails.setFullname("Project Waffle");
        userdetails.setCity("Manila");
        userdetails.setCountry("Philippines");
        userdetails.setEmailaddress("pwaffle@gmail.com");
        userdetails.setLocale("EN_US");
        

        final User user = new User();
        user.setUsergroup(usergroup);
        user.setClient(client);
        user.setUserdetails(userdetails);
        user.setName("administrator");
        user.setPasswordexpiration(now.getTime());
        user.setProfileexpiration(now.getTime());
        return this.userService.saveOrUpdate(user, "password1234");
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

        this.generateConfiguration(client);

        this.generateUser(usergroup, client);
    }

}
