package framework.core.persistence;

import java.util.Date;
import java.util.List;

import framework.core.entity.Session;

public interface SessionDao extends Dao<Session> {
    
    List<Session> findSessionById(String id);
    
    List<Session> findSessionByUser(String username);
    
    List<Session> findExpiredSessions(Date time);
}
