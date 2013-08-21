package framework.core.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.entity.Session;
import framework.core.persistence.SessionDao;

@Named
public class SessionDaoImpl extends AbstractDao<Session> implements SessionDao {

    private static final long serialVersionUID = -1385640701108483561L;

    @Override
    public List<Session> findBySessionId(String sessionId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("sessionid", sessionId);
        return this.find("findBySessionId", parameters);
    }

}
