package framework.core.tasks;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.auditlog.AuditlogService;
import framework.core.domain.session.SessionService;

@Named
public class TaskExecutor {

    private final List<Task> tasks;
    private AuditlogService auditlogService;
    private SessionService sessionService;
    
    @Inject
    protected TaskExecutor(AuditlogService auditlogService, SessionService sessionService, List<Task> tasks) {
        this.auditlogService = auditlogService;
        this.sessionService = sessionService;
        this.tasks = tasks;
    }

    @PostConstruct
    public void performScheduledJobs() {
        this.sessionService.deleteActiveSessions();
        Thread thread = new Thread(new TaskThread(auditlogService, tasks));
        thread.start();
    }
}
