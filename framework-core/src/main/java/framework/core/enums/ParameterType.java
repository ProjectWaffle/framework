package framework.core.enums;

import framework.core.entity.Reference;

/**
 * Allowable types of System Parameter.
 * 
 * @author Frederick Yap
 */
public enum ParameterType {

    /**
     * The parameter type is {@link Boolean}.
     */
    BOOLEAN,

    /**
     * The parameter type is basically {@link String}, but the validation must check if it is an email address.
     */
    EMAIL,

    /**
     * The parameter type is {@link Long}.
     */
    NUMERIC,

    PASSWORD,

    /**
     * The parameter type is {@link Reference}. The {@link ReferenceType} is based on the {@link ParameterCode}.
     */
    SELECTION,

    /**
     * The parameter type is just a {@link String}.
     */
    STRING
}