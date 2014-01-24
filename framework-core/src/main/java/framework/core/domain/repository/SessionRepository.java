package framework.core.domain.repository;

import java.util.List;

import framework.core.domain.model.Session;

public interface SessionRepository extends Repository<Session> {

    List<Session> findActiveSessionById(String id);

    List<Session> findActiveSessions();

    List<Session> findActiveSessionsByUser(String username);

    List<Session> findExpiredSessions();
}
