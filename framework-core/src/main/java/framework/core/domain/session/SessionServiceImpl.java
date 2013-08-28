package framework.core.domain.session;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.domain.ServiceImpl;
import framework.core.domain.systemparameter.SystemParameter;
import framework.core.domain.systemparameter.SystemParameterService;
import framework.core.domain.user.User;

@Named
public class SessionServiceImpl extends ServiceImpl<Session> implements SessionService {

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
        this.delete(this.sessionDao.findExpiredSessions(Calendar.getInstance().getTime()));
    }

    @Override
    public Session findSessionById(String username, String id) {
        final List<Session> sessions = this.sessionDao.findSessionById(id);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            if ((username.equals(session.getUser().getName())) && (Calendar.getInstance().getTime().before(session.getExpiry()))) {
                return session;
            }
        }
        return null;
    }

    @Override
    public String saveOrUpdate(User user) {
        Session session = new Session();
        String token = "";
        Calendar calendar = Calendar.getInstance();
        final List<Session> sessions = this.sessionDao.findSessionByUser(user.getName());
        if (sessions.size() == 1) {
            session = sessions.get(0);
        }
        final SystemParameter systemParameter = this.systemParameterService.findSystemParamByCode(ParameterCode.SESSION_TIMEOUT, user.getClient().getName());
        final Integer valueToAdd = Integer.valueOf(systemParameter.getValue());
        session.setUser(user);
        session.setStart(calendar.getTime());
        calendar.add(Calendar.MINUTE, valueToAdd);
        session.setExpiry(calendar.getTime());
        token = String.format("sessionid=%s;username=%s", session.getId(), user.getName());
        this.saveOrUpdate(session);
        return this.getCryptography().encrypt(token);
    }
}
