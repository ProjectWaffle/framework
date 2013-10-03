package framework.core.constants;

/**
 * Enumerated values for different system events.
 * 
 * @author Frederick Yap
 */
public enum EventType {
    /**
     * This is the event type if a data is deleted in the datastore.
     */
    DELETE,

    /**
     * This is the event type if there is an unhandled exception encountered.
     */
    EXCEPTION,

    /**
     * This is the event type if a new data is persisted in the datastore or an existing data has been updated.
     */
    INSERT,

    /**
     * This is the event type if an existing data has been updated.
     */
    UPDATE,
    
    /**
     * This is the event type when a job is executed.
     */
    JOB_EXECUTION
}