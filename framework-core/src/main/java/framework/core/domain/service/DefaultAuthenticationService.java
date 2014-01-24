package framework.core.domain.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.domain.model.Configuration;
import framework.core.domain.model.Credential;
import framework.core.domain.model.ReferenceCode;
import framework.core.domain.model.Session;
import framework.core.domain.repository.ConfigurationRepository;
import framework.core.domain.repository.SessionRepository;
import framework.core.domain.repository.UserRepository;
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
class DefaultAuthenticationService implements AuthenticationService {

    private static final long serialVersionUID = 5506093159372637005L;

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final ConfigurationRepository auditlogRepository;

    @Inject
    protected DefaultAuthenticationService(UserRepository userRepository, SessionRepository sessionRepository, ConfigurationRepository auditlogRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.auditlogRepository = auditlogRepository;
    }

    @Override
    public Session authenticate(String username, String password) {
        final Credential credential = this.validateLogin(username, password);
        return this.saveOrUpdate(credential);
    }

    @Override
    public Credential findCredentialByUsername(String username) {
        final List<Credential> credential = this.userRepository.findCredentialsByName(username);
        if (credential.size() == 1) {
            return credential.get(0);
        }
        return null;
    }

    @Override
    public void logout(Credential credential) {
        this.sessionRepository.delete(this.findActiveSessionByUser(credential));
    }

    @Override
    public void logoutExpiredSession() {
        final List<Session> sessions = this.sessionRepository.findExpiredSessions();
        for (final Session session : sessions) {
            this.logout(session.getCredential());
        }
        this.sessionRepository.delete(sessions);
    }

    /**
     * @deprecated using Jersey validation instead.
     * @param username
     * @param password
     * @return
     */
    protected Credential validateLogin(String username, String password) {
        final Credential credential = this.findCredentialByUsername(username);
        final Date now = Calendar.getInstance().getTime();
        if (credential == null) {
            throw new InvalidUserException("Unable to find username matching [" + username + "]");
        }
        if (!this.findActiveSessionByUser(credential).isEmpty()) {
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

    protected List<Session> findActiveSessionByUser(Credential user) {
        return this.sessionRepository.findActiveSessionsByUser(user.getName());
    }
    
    public Session saveOrUpdate(Credential credential) {
        Session session = new Session();
        final Calendar now = Calendar.getInstance();
        final List<Session> sessions = this.findActiveSessionByUser(credential);
        if (sessions.size() == 1) {
            session = sessions.get(0);
        }
        final Configuration configuration = this.auditlogRepository.findConfigurationByCodeAndClient(
                ReferenceCode.CONFIGURATION_SESSION_TIMEOUT, credential.getClient().getName());
        final Integer valueToAdd = Integer.valueOf(configuration.getValue());
        session.setCredential(credential);
        session.setStart(now.getTime());
        now.add(Calendar.MINUTE, valueToAdd);
        session.setExpiry(now.getTime());
        return this.sessionRepository.saveOrUpdate(session);
    }
    
    @Override
    public Session findSessionById(String username, String id) {
        final List<Session> sessions = this.sessionRepository.findActiveSessionById(id);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            if ((username.equals(session.getCredential().getName()))
                    && (Calendar.getInstance().getTime().before(session.getExpiry()))) {
                return session;
            }
        }
        return null;
    }
}
