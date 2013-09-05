package framework.core.tasks.examples;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.session.SessionService;
import framework.core.tasks.Task;

@Named
public class SessionMonitorTask implements Task {

    private static final long serialVersionUID = -2744188112114470973L;
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
    public void performJob() {
        this.sessionService.deleteExpiredSessions();
    }

}
