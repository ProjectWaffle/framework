package framework.core.domain.job;

import java.util.List;

import framework.core.domain.Dao;

interface JobDao extends Dao<Job> {

    List<Job> findJobByName(String name);
}
