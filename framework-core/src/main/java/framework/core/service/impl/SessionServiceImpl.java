package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Session;
import framework.core.enums.ParameterCode;
import framework.core.persistence.SessionDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;

@Named
public class SessionServiceImpl extends AbstractService<Session> implements SessionService {

    private static final long serialVersionUID = -6724981340291285304L;
    private final SessionDao sessionDao;

    private SystemParameterService systemParameterService;

    @Inject
    protected SessionServiceImpl(SessionDao sessionDao) {
        super(sessionDao);
        this.sessionDao = sessionDao;
    }

    @Override
    public Session extendSession(String username, String sessionid) {
        final Session session = this.findSession(username, sessionid);
        if (session != null) {
            final String paramValue = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT).getValue();
            final Integer add = Integer.valueOf(this.getCryptography().decrypt(paramValue));
            session.setExpiry(this.getDateUtils().addSecondsUnixTime(add * 60));
            return this.sessionDao.saveOrUpdate(session);
        }
        return null;
    }

    @Override
    public Session findSession(String username, String sessionid) {
        final List<Session> sessions = this.sessionDao.findBySessionId(sessionid);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            if ((username.equals(session.getUser().getName())) && (this.getDateUtils().isBefore(session.getExpiry()))) {
                return session;
            }
        }
        return null;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }
}
