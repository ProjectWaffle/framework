package framework.core.tasks;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.job.Job;
import framework.core.domain.job.JobService;

@Named
public abstract class BaseTask {

    private JobService jobsService;

    public final void execute() {
        final Long startTime = Calendar.getInstance().getTimeInMillis();
        Job job = this.jobsService.findJobByName(this.getName());
        if (!job.isLocked()) {
            job.setLocked(true);
            job = this.jobsService.saveOrUpdate(job);
            this.performJob();
            job.setExecutiontime(Calendar.getInstance().getTimeInMillis() - startTime);
            job.setLocked(false);
            this.jobsService.saveOrUpdate(job);
        }

    }

    public abstract int priority();

    protected String getName() {
        return this.getClass().getSimpleName();
    }

    protected abstract void performJob();

    @Inject
    protected void setJobsService(JobService jobsService) {
        this.jobsService = jobsService;
    }

}
