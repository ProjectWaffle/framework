package framework.core.domain.session;

import java.util.List;

import framework.core.domain.Service;
import framework.core.domain.user.Credential;

public interface SessionService extends Service<Session> {

    List<Session> findExpiredSessions();

    void delete(Credential user);

    void deleteActiveSessions();

    List<Session> findActiveSessionByUser(Credential user);

    Session findSessionById(String username, String id);

    Session saveOrUpdate(Credential user);
    
    List<Session> findActiveSessions();
}
