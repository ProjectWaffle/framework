package framework.core.domain.job;

import framework.core.domain.Service;

public interface JobService extends Service<Job> {

    Job findJobByName(String name);
    
}
