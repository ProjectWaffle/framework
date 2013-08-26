package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Job;
import framework.core.persistence.JobDao;
import framework.core.service.JobService;

@Named
public class JobServiceImpl extends AbstractService<Job> implements JobService {

    private static final long serialVersionUID = -1247877062672555397L;

    private final JobDao jobsDao;

    @Inject
    protected JobServiceImpl(JobDao jobsDao) {
        super(jobsDao);
        this.jobsDao = jobsDao;
    }

    @Override
    public Job findJobByName(String name) {
        final List<Job> jobs = this.jobsDao.findJobByName(name);
        if (jobs.size() == 1) {
            return jobs.get(0);
        } else {
            final Job job = new Job();
            job.setName(name);
            job.setLocked(false);
            job.setExecutiontime(0L);
            return job;
        }
    }

}
