package framework.core.tasks.examples;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.user.UserService;
import framework.core.tasks.Task;

@Named
public class SessionMonitorTask extends Task {

    private static final long serialVersionUID = -2744188112114470973L;
    private final UserService userService;

    @Inject
    protected SessionMonitorTask(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int delay() {
        return 60000;
    }

    @Override
    public void performTask() {
        this.userService.logoutExpiredSession();
    }

    @Override
    public int order() {
        return 0;
    }

}
