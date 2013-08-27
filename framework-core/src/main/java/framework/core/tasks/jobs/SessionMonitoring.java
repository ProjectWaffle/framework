package framework.core.tasks.jobs;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.service.SessionService;

@Named
public class SessionMonitoring extends AbstractJob {

    private final SessionService sessionService;

    @Inject
    protected SessionMonitoring(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public JobPriority priority() {
        return JobPriority.HIGH;
    }

    @Override
    protected void performJob() {
        this.sessionService.deleteExpiredSessions();
    }

}
