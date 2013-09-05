package framework.core.tasks;

import java.io.Serializable;

import javax.inject.Named;

@Named
public interface Task extends Serializable {

    int priority();

    void performJob();

}
