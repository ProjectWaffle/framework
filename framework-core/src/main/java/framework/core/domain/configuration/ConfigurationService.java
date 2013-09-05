package framework.core.domain.configuration;

import java.util.List;

import framework.core.domain.Service;

public interface ConfigurationService extends Service<Configuration> {

    List<Configuration> findAllActiveConfiguration(String clientName);

    Configuration findConfigurationByCodeAndClient(String refCode, String clientName);

    Configuration findDatabaseVersion();

    void saveOrUpdate(String refCode, String clientName, String value);
}
