package framework.core.domain.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
class ConfigurationDaoImpl extends DaoImpl<Configuration> implements ConfigurationDao {

    private static final long serialVersionUID = -7635229511504452985L;

    @Override
    public List<Configuration> findConfigurationByRefCodeAndClient(String refCode, String clientName) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("refCode", refCode);
        parameters.put("clientName", clientName);
        return this.find("findConfigurationByRefCodeAndClient", parameters);
    }

    @Override
    public List<Configuration> findDatabaseVersion() {
        return this.find("findDatabaseVersion");
    }

    @Override
    public List<Configuration> findAllActiveConfiguration(String clientName) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("clientName", clientName);
        return this.find("findAllActiveConfiguration", parameters);
    }

}
