package framework.core.persistence.impl;

import java.util.Date;
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
    public List<Session> findSessionById(String id) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        return this.find("findSessionById", parameters);
    }

    @Override
    public List<Session> findSessionByUser(String username) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return this.find("findSessionByUser", parameters);
    }

    @Override
    public List<Session> findExpiredSessions(Date expiry) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("expiry", expiry);
        return this.find("findExpiredSessions", parameters);
    }

}
