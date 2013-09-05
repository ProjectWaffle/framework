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
    public void deleteActiveSessions() {
        this.executeUpdate("deleteActiveSessions");
    }

    @Override
    public void deleteExpiredSessions() {
        this.executeUpdate("deleteExpiredSessions");
    }

    @Override
    public List<Session> findActiveSessionById(String id) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        return this.find("findActiveSessionById", parameters);
    }

    @Override
    public List<Session> findActiveSessions() {
        return this.find("findActiveSessions");
    }

    @Override
    public List<Session> findActiveSessionsByUser(String username) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);
        return this.find("findActiveSessionByUser", parameters);
    }

    @Override
    public List<Session> findExpiredSessions() {
        return this.find("findExpiredSessions");
    }

}
