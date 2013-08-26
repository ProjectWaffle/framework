package framework.core.tasks.jobs;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.service.SessionService;

@Named
public class SessionMonitoring extends AbstractJob {

    @Inject
    private SessionService sessionService;
    
    @Override
    protected void performJob() {
        this.sessionService.deleteExpiredSessions();
    }

}
