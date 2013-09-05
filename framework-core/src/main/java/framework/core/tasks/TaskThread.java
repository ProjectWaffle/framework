package framework.core.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import framework.core.domain.auditlog.Auditlog;
import framework.core.domain.auditlog.AuditlogService;

public class TaskThread implements Runnable {

    private List<Task> tasks;
    private AuditlogService auditlogService;

    protected TaskThread(AuditlogService auditlogService, List<Task> tasks) {
        this.tasks = tasks;
        this.auditlogService = auditlogService;
        Collections.sort(tasks, new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                return task1.priority() - task2.priority();
            }
        });
    }

    @Override
    public void run() {
        try {
            for (final Task task : this.tasks) {
                task.performJob();
            }
            Thread.sleep(60000);
        } catch (final InterruptedException e) {
            auditlogService.saveOrUpdate(new Auditlog(e));
        }
        this.run();
    }

}
