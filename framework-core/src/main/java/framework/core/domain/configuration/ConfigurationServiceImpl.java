package framework.core.domain.configuration;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ReferenceCode;
import framework.core.domain.ServiceImpl;
import framework.core.domain.user.User;
import framework.core.utilities.EncryptionUtil;

@Named
class ConfigurationServiceImpl extends ServiceImpl<Configuration> implements ConfigurationService {

    private static final long serialVersionUID = 8428893570817513037L;
    private final ConfigurationDao configurationDao;
    private EncryptionUtil encryptionUtil;

    @Inject
    protected ConfigurationServiceImpl(ConfigurationDao configurationDao, EncryptionUtil encryptionUtil) {
        super(configurationDao);
        this.configurationDao = configurationDao;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public List<Configuration> findAllActiveConfiguration(User authenticatedUser) {
        List<Configuration> configurations = this.configurationDao.findAllActiveConfiguration(authenticatedUser.getClient().getName());
        for (Configuration configuration : configurations) {
            configuration.setValue(encryptionUtil.getDecryptedString(configuration.getValue()));
        }
        return configurations;
    }

    @Override
    public Configuration findConfigurationByRefCodeAndClient(String refCode, String clientName) {
        final List<Configuration> configurations = this.configurationDao.findConfigurationByRefCodeAndClient(refCode, clientName);
        Configuration configuration = null;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
            configuration.setValue(encryptionUtil.getDecryptedString(configuration.getValue()));
        }
        return configuration;
    }

    @Override
    public Configuration saveOrUpdate(Configuration configuration) {
        configuration.setValue(encryptionUtil.getEncryptedString(configuration.getValue()));
        return super.saveOrUpdate(configuration);
    }

    @Override
    public Configuration findDatabaseVersion() {
        final List<Configuration> configurations = this.configurationDao.findConfigurationByCode(ReferenceCode.CONFIGURATION_DB_VERSION);
        Configuration configuration = null;
        if (configurations.size() == 1) {
            configuration = configurations.get(0);
            configuration.setValue(encryptionUtil.getDecryptedString(configuration.getValue()));
        }
        return configuration;
    }

}
