package framework.core.service;

import java.util.List;

import framework.core.entity.SystemParameter;
import framework.core.enums.ParameterCode;

/**
 * This interface represents business operations for {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
public interface SystemParameterService extends Service<SystemParameter> {

    /**
     * Returns all active {@link SystemParameter}.
     * 
     * @return all active {@link SystemParameter}.
     */
    List<SystemParameter> findAllActiveSystemParam();

    /**
     * Returns active {@link SystemParameter} based on the input {@link ParameterCode}.
     * 
     * @param code
     *            result filter.
     * @return all active {@link SystemParameter}.
     */
    SystemParameter findByCode(ParameterCode code);

}
