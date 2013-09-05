package framework.core.domain.configuration;

import java.util.List;

import framework.core.domain.Service;
import framework.core.domain.user.Credential;

public interface ConfigurationService extends Service<Configuration> {

    Configuration findConfigurationByRefCodeAndClient(String refCode, String clientName);

    Configuration findDatabaseVersion();

    List<Configuration> findAllActiveConfiguration(Credential authenticatedUser);
}
