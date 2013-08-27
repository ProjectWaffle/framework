package framework.core.tasks.jobs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobsExecutor {

    private List<AbstractJob> jobs;
    
    @Inject
    protected JobsExecutor(List<AbstractJob> jobs) {
        this.jobs = jobs;
    }
    
    public void performScheduledJobs() {
        Collections.sort(jobs, new Comparator<AbstractJob>() {

            @Override
            public int compare(AbstractJob o1, AbstractJob o2) {
                return (o1.priority().getPriority() - o2.priority().getPriority())*-1;
            }
        });
        for (AbstractJob job : jobs) {
            job.execute();
        }
    }
}
