package framework.core.tasks.jobs;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Job;
import framework.core.service.JobService;
import framework.core.utilities.DateUtils;

@Named
public abstract class AbstractJob {

    public static enum JobPriority {

        HIGH(3),
        LOW(0),
        MEDIUM(1);

        private final int priority;

        JobPriority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }

    }

    private DateUtils dateUtils;

    private JobService jobsService;

    public final void execute() {
        final Long startTime = this.dateUtils.getCurrentUnixTime();
        Job job = this.jobsService.findJobByName(this.getName());
        if (!job.isLocked()) {
            job.setLocked(true);
            job = this.jobsService.saveOrUpdate(job);
            this.performJob();
            job.setExecutiontime(this.dateUtils.getCurrentUnixTime() - startTime);
            job.setLocked(false);
            this.jobsService.saveOrUpdate(job);
        }

    }

    public abstract JobPriority priority();

    protected String getName() {
        return this.getClass().getSimpleName();
    }

    protected abstract void performJob();

    @Inject
    protected void setDateUtils(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    @Inject
    protected void setJobsService(JobService jobsService) {
        this.jobsService = jobsService;
    }

}
