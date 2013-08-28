package framework.core.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TaskExecutor {

    private final List<BaseTask> jobs;

    @Inject
    protected TaskExecutor(List<BaseTask> jobs) {
        this.jobs = jobs;
    }

    public void performScheduledJobs() {
        Collections.sort(this.jobs, new Comparator<BaseTask>() {

            @Override
            public int compare(BaseTask o1, BaseTask o2) {
                return (o1.priority() - o2.priority()) * -1;
            }
        });
        for (final BaseTask job : this.jobs) {
            job.execute();
        }
    }
}
