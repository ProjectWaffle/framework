package framework.core.persistence;

import java.util.List;

import framework.core.entity.Job;

public interface JobDao extends Dao<Job> {

    List<Job> findJobByName(String name);
}
