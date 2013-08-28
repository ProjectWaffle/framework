package framework.core.tasks;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.session.SessionService;

@Named
public class SessionMonitorTask extends BaseTask {

    private final SessionService sessionService;

    @Inject
    protected SessionMonitorTask(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    protected void performJob() {
        this.sessionService.deleteExpiredSessions();
    }

}
