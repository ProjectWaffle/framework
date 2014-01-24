package framework.core.domain.repository;

import java.util.List;

import framework.core.domain.model.Configuration;

public interface ConfigurationRepository extends Repository<Configuration> {

    Configuration findConfigurationByCodeAndClient(String refCode, String clientName);

    Configuration  findConfigurationByCode(String code);

    List<Configuration>  findAllActiveConfiguration(String name);
}
