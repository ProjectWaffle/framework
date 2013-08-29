package framework.core.domain.session;

import framework.core.domain.Service;
import framework.core.domain.user.User;

public interface SessionService extends Service<Session> {

    Session findSessionById(String username, String id);

    Session saveOrUpdate(User user);
    
    void deleteExpiredSessions();
}
