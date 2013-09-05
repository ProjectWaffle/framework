package framework.core.domain.session;

import java.util.List;

import framework.core.domain.Service;
import framework.core.domain.user.Credential;

public interface SessionService extends Service<Session> {

    void delete(Credential user);

    void deleteExpiredSessions();

    List<Session> findActiveSessionByUser(Credential user);

    Session findSessionById(String username, String id);

    Session saveOrUpdate(Credential user);
}
