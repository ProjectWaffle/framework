package framework.core.domain.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import framework.core.domain.BaseEntity;

@Entity
@Table(name = "JOBS")
@NamedQueries(value = { @NamedQuery(name = "findJobsByName", query = "from Job where name=:name") })
public class Job extends BaseEntity {

    private static final long serialVersionUID = -672481386694874052L;

    @Column
    private Long executiontime;

    @Column
    private boolean locked;

    @Column(unique = true)
    private String name;

    public Long getExecutiontime() {
        return this.executiontime;
    }

    public String getName() {
        return this.name;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setExecutiontime(Long executiontime) {
        this.executiontime = executiontime;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setName(String name) {
        this.name = name;
    }
}
