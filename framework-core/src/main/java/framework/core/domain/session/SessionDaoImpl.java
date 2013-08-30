package framework.core.domain.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.domain.DaoImpl;

@Named
class SessionDaoImpl extends DaoImpl<Session> implements SessionDao {

    private static final long serialVersionUID = -1385640701108483561L;

    @Override
    public List<Session> findActiveSessionById(String id) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        return this.find("findActiveSessionById", parameters);
    }

    @Override
    public List<Session> findActiveSessionsByUser(String username) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return this.find("findActiveSessionByUser", parameters);
    }

    @Override
    public List<Session> findExpiredSessions() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        return this.find("findExpiredSessions", parameters);
    }

}
