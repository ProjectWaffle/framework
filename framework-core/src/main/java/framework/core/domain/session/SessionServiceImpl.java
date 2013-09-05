package framework.core.domain.session;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ReferenceCode;
import framework.core.domain.ServiceImpl;
import framework.core.domain.configuration.Configuration;
import framework.core.domain.configuration.ConfigurationService;
import framework.core.domain.user.Credential;

@Named
class SessionServiceImpl extends ServiceImpl<Session> implements SessionService {

    private static final long serialVersionUID = -6724981340291285304L;
    private final ConfigurationService configurationService;
    private final SessionDao sessionDao;

    @Inject
    protected SessionServiceImpl(SessionDao sessionDao, ConfigurationService configurationService) {
        super(sessionDao);
        this.sessionDao = sessionDao;
        this.configurationService = configurationService;
    }

    @Override
    public void delete(Credential user) {
        this.delete(this.findActiveSessionByUser(user));
    }

    @Override
    public void deleteExpiredSessions() {
        this.delete(this.sessionDao.findExpiredSessions());
    }

    @Override
    public List<Session> findActiveSessionByUser(Credential user) {
        return this.sessionDao.findActiveSessionsByUser(user.getName());
    }

    @Override
    public Session findSessionById(String username, String id) {
        final List<Session> sessions = this.sessionDao.findActiveSessionById(id);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            if ((username.equals(session.getUser().getName()))
                    && (Calendar.getInstance().getTime().before(session.getExpiry()))) {
                return session;
            }
        }
        return null;
    }

    @Override
    public Session saveOrUpdate(Credential user) {
        Session session = new Session();
        final Calendar now = Calendar.getInstance();
        final List<Session> sessions = this.findActiveSessionByUser(user);
        if (sessions.size() == 1) {
            session = sessions.get(0);
        }
        final Configuration configuration = this.configurationService.findConfigurationByRefCodeAndClient(
                ReferenceCode.CONFIGURATION_SESSION_TIMEOUT, user.getClient().getName());
        final Integer valueToAdd = Integer.valueOf(configuration.getValue());
        session.setUser(user);
        session.setStart(now.getTime());
        now.add(Calendar.MINUTE, valueToAdd);
        session.setExpiry(now.getTime());
        return this.saveOrUpdate(session);
    }
}
