package framework.core.domain.session;

import java.util.List;

import framework.core.domain.Dao;

public interface SessionDao extends Dao<Session> {
    
    List<Session> findActiveSessionById(String id);
    
    List<Session> findActiveSessionByUser(String username);
    
    List<Session> findExpiredSessions();
}
