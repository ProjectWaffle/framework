package framework.core.domain.service;

import java.io.Serializable;
import java.util.List;

import framework.core.domain.model.Configuration;

public interface ConfigurationService extends Serializable {

    List<Configuration> findAllActiveConfiguration(String clientName);

    Configuration findConfigurationByCodeAndClient(String refCode, String clientName);

    Configuration findDatabaseVersion();

    void saveOrUpdate(String refCode, String clientName, String value);
}
