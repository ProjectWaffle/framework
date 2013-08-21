package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.SystemParameter;
import framework.core.enums.ParameterCode;
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
    public List<SystemParameter> findAllActiveSystemParam() {
        List<SystemParameter> systemParameters = this.systemParameterDao.findAllActiveSystemParam();
        for (SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(this.getCryptography().decrypt(systemParameter.getValue()));
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
    public SystemParameter findByCode(ParameterCode code) {
        final List<SystemParameter> systemParameters = this.systemParameterDao.findSystemParametersByCode(code);
        if (systemParameters.size() > 0) {
            return systemParameters.get(0);
        }
        return null;
    }

}
