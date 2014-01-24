package framework.core.domain.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.model.Configuration;
import framework.core.domain.model.ReferenceCode;
import framework.core.domain.repository.ConfigurationRepository;

@Named
class DefaultConfigurationService implements ConfigurationService {

    private static final long serialVersionUID = 8428893570817513037L;
    private final ConfigurationRepository configurationRepository;

    @Inject
    protected DefaultConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public     List<Configuration> findAllActiveConfiguration(String clientName) {
        return this.configurationRepository.findAllActiveConfiguration(clientName);
    }

    @Override
    public Configuration findConfigurationByCodeAndClient(String refCode, String clientName) {
        return this.configurationRepository.findConfigurationByCodeAndClient(refCode, clientName);
    }

    @Override
    public Configuration findDatabaseVersion() {
    	return this.configurationRepository.findConfigurationByCode(ReferenceCode.CONFIGURATION_DB_VERSION);
    }

    @Override
    public void saveOrUpdate(String refCode, String clientName, String value) {
        final Configuration configuration = this.findConfigurationByCodeAndClient(refCode, clientName);
        if (configuration != null) {
            configuration.setValue(value);
            this.configurationRepository.saveOrUpdate(configuration);
        }
    }

}
