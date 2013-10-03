package framework.core.domain.user;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.ServiceImpl;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;
import framework.core.exceptions.CredentialExpiredException;
import framework.core.exceptions.InvalidUserException;
import framework.core.exceptions.SessionExistException;
import framework.core.exceptions.UserProfileExpiredException;

/**
 * Performs business operations for {@link Credential} entity/
 * 
 * @author Frederick Yap
 */
@Named
class UserServiceImpl extends ServiceImpl<Credential> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;

    private final SessionService sessionService;
    private final UserDao userDao;

    @Inject
    protected UserServiceImpl(UserDao userDao, SessionService sessionService) {
        super(userDao);
        this.userDao = userDao;
        this.sessionService = sessionService;
    }

    @Override
    public Session authenticate(String username, String password) {
        final Credential credential = this.validateLogin(username, password);
        return this.sessionService.saveOrUpdate(credential);
    }

    @Override
    public Credential findCredentialByUsername(String username) {
        final List<Credential> credential = this.userDao.findCredentialsByName(username);
        if (credential.size() == 1) {
            return credential.get(0);
        }
        return null;
    }

    @Override
    public void logout(Credential credential) {
        this.sessionService.delete(credential);
    }

    @Override
    public void logoutExpiredSession() {
        final List<Session> sessions = this.sessionService.findExpiredSessions();
        for (final Session session : sessions) {
            this.logout(session.getCredential());
        }
        this.sessionService.delete(sessions);
    }

    protected Credential validateLogin(String username, String password) {
        final Credential credential = this.findCredentialByUsername(username);
        final Date now = Calendar.getInstance().getTime();
        if (credential == null) {
            throw new InvalidUserException("Unable to find username matching [" + username + "]");
        }
        if (!this.sessionService.findActiveSessionByUser(credential).isEmpty()) {
            throw new SessionExistException(String.format("Session already exist for user [%s]", username));
        }
        if (now.after(credential.getProfileexpiration())) {
            throw new UserProfileExpiredException(String.format("User [%s] has already expired.", username));
        }
        if (now.after(credential.getPasswordexpiration())) {
            throw new CredentialExpiredException("Credentials for user " + username + " has already expired.");
        }
        if (!password.equals(credential.getPassword())) {
            throw new InvalidUserException("Password for " + username + " is invalid.");
        }
        return credential;
    }

}
