package framework.core.service.impl;

import java.util.List;

import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;
import framework.core.persistence.SystemParameterDao;
import framework.core.service.SystemParameterService;

/**
 * Performs business operations for {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class SystemParameterServiceImpl extends AbstractService<SystemParameter, SystemParameterDao> implements
        SystemParameterService {

    private static final long serialVersionUID = 1541672630059263485L;

    /*
     * (non-Javadoc)
     * @see framework.core.service.SystemParameterService#findAllActiveSystemParam()
     */
    @Override
    public List<SystemParameter> findAllActiveSystemParam() {
        return this.getPersistence().findAllActiveSystemParam();
    }

    /*
     * (non-Javadoc)
     * @see
     * framework.core.service.SystemParameterService#findSystemParametersByCode(framework.core.domain.SystemParameter
     * .ParameterCode)
     */
    @Override
    public SystemParameter findByCode(ParameterCode code) {
        final List<SystemParameter> systemParameters = this.getPersistence().findSystemParametersByCode(code);
        if (systemParameters.size() > 0) {
            return systemParameters.get(0);
        }
        return null;
    }

}
