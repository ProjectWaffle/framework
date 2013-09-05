package framework.core.tasks;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.auditlog.AuditlogService;

@Named
public class TaskExecutor {

    private final List<Task> tasks;
    private AuditlogService auditlogService;
    
    @Inject
    protected TaskExecutor(AuditlogService auditlogService, List<Task> tasks) {
        this.auditlogService = auditlogService;
        this.tasks = tasks;
    }

    @PostConstruct
    public void performScheduledJobs() {
        Thread thread = new Thread(new TaskThread(auditlogService, tasks));
        thread.start();
    }
}
