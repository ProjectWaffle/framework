package framework.core.domain.configuration;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;
import framework.core.domain.reference.ReferenceCode;

@Named
class ConfigurationServiceImpl extends ServiceImpl<Configuration> implements ConfigurationService {

    private static final long serialVersionUID = 8428893570817513037L;
    private final ConfigurationDao configurationDao;

    @Inject
    protected ConfigurationServiceImpl(ConfigurationDao configurationDao) {
        super(configurationDao);
        this.configurationDao = configurationDao;
    }

    @Override
    public List<Configuration> findAllActiveConfiguration(String clientName) {
        return this.configurationDao.findAllActiveConfiguration(clientName);
    }

    @Override
    public Configuration findConfigurationByCodeAndClient(String refCode, String clientName) {
        final List<Configuration> configurations = this.configurationDao.findConfigurationByCodeAndClient(refCode, clientName);
        Configuration configuration = null;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
        }
        return configuration;
    }

    @Override
    public Configuration findDatabaseVersion() {
        final List<Configuration> configurations = this.configurationDao.findConfigurationByCode(ReferenceCode.CONFIGURATION_DB_VERSION);
        Configuration configuration = null;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
        }
        return configuration;
    }

    @Override
    public void saveOrUpdate(String refCode, String clientName, String value) {
        final Configuration configuration = this.findConfigurationByCodeAndClient(refCode, clientName);
        if (configuration != null) {
            configuration.setValue(value);
            this.saveOrUpdate(configuration);
        }
    }

}
