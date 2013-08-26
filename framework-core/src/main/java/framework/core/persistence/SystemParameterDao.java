package framework.core.persistence;

import java.util.List;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;

/**
 * Data access interface for persisting {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
public interface SystemParameterDao extends Dao<SystemParameter> {

    /**
     * Returns all active system parameters.
     * 
     * @return list of system parameters.
     */
    List<SystemParameter> findAllActiveSystemParam();

    /**
     * Returns all active system parameters by {@link ParameterCode}.
     * 
     * @param code
     *            {@link ParameterCode} as search criteria.
     * @return list of system parameters.
     */
    List<SystemParameter> findSystemParametersByCode(ParameterCode code);

}
