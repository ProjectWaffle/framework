package framework.core.domain.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import framework.core.domain.model.Session;
import framework.core.domain.model.User;

@Named
class DefaultSessionRepository extends AbstractRepository<Session> implements SessionRepository {

    private static final long serialVersionUID = -1385640701108483561L;

    @Override
    public List<Session> findActiveSessionById(String id) {
        Root<Session> fromSession = this.getRoot();
        Predicate condition1 = getCriteriaBuilder().equal(fromSession.get("id"), id);
        return this.getResultList(condition1);
    }

    @Override
    public List<Session> findActiveSessions() {
        return this.getResultList();
    }

    @Override
    public List<Session> findActiveSessionsByUser(String username) {
        Root<Session> fromSession = this.getRoot();
        Path<Date> expiry = fromSession.get("expiry");
        Join<Session, User> joinSession = fromSession.join("credential");
        Predicate condition = getCriteriaBuilder().and(
                getCriteriaBuilder().equal(joinSession.get("name"), username),
                getCriteriaBuilder().greaterThan(expiry, getCriteriaBuilder().currentTimestamp()));
        return this.getResultList(condition);
    }

    @Override
    public List<Session> findExpiredSessions() {
        Root<Session> fromSession = this.getRoot();
        Path<Date> expiry = fromSession.get("expiry");
        Predicate condition1 = getCriteriaBuilder().lessThanOrEqualTo(expiry, getCriteriaBuilder().currentTimestamp());
        return this.getResultList(condition1);
    }

}
