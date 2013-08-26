package framework.core.service;

import framework.core.entity.Job;

public interface JobService extends Service<Job> {

    Job findJobByName(String name);
    
}
