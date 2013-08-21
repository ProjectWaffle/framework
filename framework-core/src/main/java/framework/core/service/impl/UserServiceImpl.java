package framework.core.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.enums.ParameterCode;
import framework.core.exceptions.CredentialExpiredException;
import framework.core.exceptions.InvalidUserException;
import framework.core.exceptions.UserProfileExpiredException;
import framework.core.persistence.UserDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;
    private SessionService sessionService;
    private SystemParameterService systemParameterService;

    private final UserDao userDao;

    @Inject
    protected UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public Session authenticate(String username, String password) {
        final List<User> users = this.userDao.findUsersByUsername(username);
        if (users.size() == 1) {
            return this.validateAndPrepareSession(users.get(0), password);
        }
        throw new InvalidUserException(String.format("Username [%s] does not exist.", username));
    }

    protected Session validateAndPrepareSession(User user, String password) {
        final Session session = new Session();
        final SystemParameter systemParameter = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT);
        final Integer valueToAdd = Integer.valueOf(this.getCryptography().decrypt(systemParameter.getValue()));
        if (this.getDateUtils().getCurrentUnixTime() > user.getProfileexpiration()) {
            throw new UserProfileExpiredException(String.format("User [%s] has already expired.", user.getName()));
        }
        if (this.getDateUtils().getCurrentUnixTime() > user.getPasswordexpiration()) {
            throw new CredentialExpiredException("Credentials for user " + user.getName() + " has already expired.");
        }
        if (!password.equals(this.getCryptography().decrypt(user.getPassword()))) {
            throw new InvalidUserException("Password for " + user.getName() + " is invalid.");
        }
        session.setUser(user);
        session.setSessionid(UUID.randomUUID().toString());
        session.setStart(this.getDateUtils().getCurrentUnixTime());
        session.setExpiry(this.getDateUtils().addSecondsUnixTime(valueToAdd*60));
        return this.sessionService.saveOrUpdate(session);
    }

    @Inject
    protected void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

}
