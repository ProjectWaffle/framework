package framework.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.Client;
import framework.core.entity.SystemParameter;
import framework.core.persistence.SystemParameterDao;
import framework.core.service.SystemParameterService;

/**
 * Performs business operations for {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class SystemParameterServiceImpl extends AbstractService<SystemParameter> implements SystemParameterService {

    private static final long serialVersionUID = 1541672630059263485L;

    private final SystemParameterDao systemParameterDao;

    @Inject
    protected SystemParameterServiceImpl(SystemParameterDao systemParameterDao) {
        super(systemParameterDao);
        this.systemParameterDao = systemParameterDao;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.SystemParameterService#findAllActiveSystemParam()
     */
    @Override
    public List<SystemParameter> findAllActiveSystemParam(String clientName) {
        final List<SystemParameter> systemParameters = new ArrayList<SystemParameter>();
        final Calendar now = Calendar.getInstance();
        for (final SystemParameter systemParameter : this.systemParameterDao.findAllActiveSystemParam()) {
            final Client client = systemParameter.getClient();
            if (client != null) {
                if (clientName.equals(client.getName()) && now.getTime().before(client.getValidity())) {
                    systemParameter.setValue(this.getCryptography().decrypt(systemParameter.getValue()));
                    systemParameters.add(systemParameter);
                }
            }
        }
        return systemParameters;
    }

    /*
     * (non-Javadoc)
     * @see
     * framework.core.service.SystemParameterService#findSystemParametersByCode(framework.core.domain.SystemParameter
     * .ParameterCode)
     */
    @Override
    public SystemParameter findSystemParamByCode(ParameterCode code, String clientName) {
        final List<SystemParameter> systemParameters = this.systemParameterDao.findSystemParametersByCode(code);
        final Calendar now = Calendar.getInstance();
        if (systemParameters.size() > 0) {
            final SystemParameter systemParameter = systemParameters.get(0);
            final Client client = systemParameter.getClient();
            if (client != null) {
                if (clientName.equals(client.getName()) && now.getTime().before(client.getValidity())) {
                    systemParameter.setValue(this.getCryptography().decrypt(systemParameter.getValue()));
                    return systemParameter;
                }
            }
        }
        return null;
    }

    @Override
    public SystemParameter findDatabaseVersion() {
        final List<SystemParameter> systemParameters = this.systemParameterDao.findSystemParametersByCode(ParameterCode.DB_VERSION);
        if (systemParameters.size() > 0) {
            return systemParameters.get(0);
        }
        return null;
    }
}
