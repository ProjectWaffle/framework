package framework.core.tasks;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TaskScheduler extends Thread {

    private final List<Task> tasks;

    @Inject
    protected TaskScheduler(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        Collections.sort(tasks);
        for (final Task task : this.tasks) {
            task.start();
        }
    }
    
    @PostConstruct
    public final void execute() {
        this.start();
    }

}
