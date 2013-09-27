package framework.core.domain.session;

import java.util.List;

import framework.core.domain.Dao;

interface SessionDao extends Dao<Session> {

    List<Session> findActiveSessionById(String id);

    List<Session> findActiveSessions();

    List<Session> findActiveSessionsByUser(String username);

    List<Session> findExpiredSessions();
}
