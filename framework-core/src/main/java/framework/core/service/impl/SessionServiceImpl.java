package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.persistence.SessionDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;

@Named
public class SessionServiceImpl extends AbstractService<Session> implements SessionService {

    private static final long serialVersionUID = -6724981340291285304L;
    private final SessionDao sessionDao;
    private SystemParameterService systemParameterService;

    @Inject
    protected SessionServiceImpl(SessionDao sessionDao, SystemParameterService systemParameterService) {
        super(sessionDao);
        this.sessionDao = sessionDao;
        this.systemParameterService = systemParameterService;
    }

    @Override
    public void deleteExpiredSessions() {
        this.delete(this.sessionDao.findExpiredSessions(this.getDateUtils().getCurrentUnixTime()));
    }

    @Override
    public Session findSessionById(String username, String id) {
        final List<Session> sessions = this.sessionDao.findSessionById(id);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            if ((username.equals(session.getUser().getName())) && (this.getDateUtils().isBefore(session.getExpiry()))) {
                return session;
            }
        }
        return null;
    }

    @Override
    public String saveOrUpdate(User user) {
        Session session = new Session();
        String token = "";
        final List<Session> sessions = this.sessionDao.findSessionByUser(user.getName());
        if (sessions.size() == 1) {
            session = sessions.get(0);
        }
        final SystemParameter systemParameter = this.systemParameterService.findSystemParamByCode(ParameterCode.SESSION_TIMEOUT, user.getClient().getName());
        final Integer valueToAdd = Integer.valueOf(systemParameter.getValue());
        session.setUser(user);
        session.setStart(this.getDateUtils().getCurrentUnixTime());
        session.setExpiry(this.getDateUtils().addSecondsUnixTime(valueToAdd * 60));
        token = String.format("sessionid=%s;username=%s", session.getId(), user.getName());
        this.saveOrUpdate(session);
        return this.getCryptography().encrypt(token);
    }
}
