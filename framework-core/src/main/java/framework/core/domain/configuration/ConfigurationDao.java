package framework.core.domain.configuration;

import java.util.List;

import framework.core.domain.Dao;

interface ConfigurationDao extends Dao<Configuration> {

    List<Configuration> findConfigurationByRefCodeAndClient(String refCode, String clientName);

    List<Configuration> findDatabaseVersion();

    List<Configuration> findAllActiveConfiguration(String name);
}
