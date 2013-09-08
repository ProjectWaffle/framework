package framework.core.tasks;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * Extends this class to create a simple background task. This class should not be used as an alternative to complicated
 * task like creation of CSV files, or ETL functionalities.
 * 
 * @author Frederick Yap
 * 
 */
@Named
public abstract class Task extends Thread implements Comparable<Task>, Serializable {

    private static final long serialVersionUID = 4107027126655930113L;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public int compareTo(Task task) {
        return this.order() - task.order();
    }

    /**
     * The time period in millis when this task will be executed again.
     * 
     * @return period in millis.
     */
    public abstract int delay();

    /**
     * Perform the actual tasks.
     */
    public abstract void performTask();

    /**
     * The order at which this task is executed by the scheduler. Lower numbers means they get executed first.
     * 
     * @return order at which this task is executed by the scheduler.
     */
    public abstract int order();

    @Override
    public void run() {
        try {
            this.performTask();
            Thread.sleep(this.delay());
        } catch (final InterruptedException e) {
            this.logger.log(Level.SEVERE, "Background task is interruped.", e);
        } finally {
            this.run();
        }
    }

}
