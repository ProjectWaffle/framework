package framework.core.utilities;

import javax.inject.Named;

/**
 * Base class for data generator classes.
 * 
 * @author Frederick Yap
 */
@Named
public abstract class DataGenerator {

    /**
     * Returns the new database version. Normally, the database version is supplied by <em>DBVersion{}.data</em> file.
     * However, for some cases, it is necessary to override this method especially if no <em>DBVersion{}.data</em> is
     * provided.
     * 
     * @return the new database version.
     */
    protected abstract Integer getDBVersion();

    /**
     * Override this method to perform additional tasks.
     */
    protected abstract void performDataOperation();

}
