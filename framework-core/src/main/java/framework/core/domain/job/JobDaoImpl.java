package framework.core.domain.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
class JobDaoImpl extends DaoImpl<Job> implements JobDao {

    private static final long serialVersionUID = 1104856385879275119L;

    @Override
    public List<Job> findJobByName(String name) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        return this.find("findJobsByName", parameters);
    }

}
