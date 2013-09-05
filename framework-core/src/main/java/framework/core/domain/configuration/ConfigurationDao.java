package framework.core.domain.configuration;

import java.util.List;

import framework.core.domain.Dao;

interface ConfigurationDao extends Dao<Configuration> {

    List<Configuration> findConfigurationByCodeAndClient(String refCode, String clientName);

    List<Configuration> findConfigurationByCode(String code);

    List<Configuration> findAllActiveConfiguration(String name);
}
