package framework.core.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.User;
import framework.core.persistence.UserDao;
import framework.core.service.SessionService;
import framework.core.service.UserService;
import framework.core.service.impl.exceptions.CredentialExpiredException;
import framework.core.service.impl.exceptions.InvalidUserException;
import framework.core.service.impl.exceptions.UserProfileExpiredException;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;
    
    private SessionService sessionService;

    private final UserDao userDao;

    @Inject
    protected UserServiceImpl(UserDao userDao, SessionService sessionService) {
        super(userDao);
        this.userDao = userDao;
        this.sessionService = sessionService;
    }

    @Override
    public String authenticate(String username, String password) {
        final User user = this.validateLogin(username, password);
        return this.sessionService.saveOrUpdate(user);
    }

    @Override
    public User findUserByUsername(String username) {
        final List<User> users = this.userDao.findUsersByName(username);
        if (users.size() == 1) {
            return users.get(0);
        }
        throw new InvalidUserException("Unable to find username matching [" + username + "]");
    }

    protected User validateLogin(String username, String password) {
        final User user = this.findUserByUsername(username);
        Date now = Calendar.getInstance().getTime();
        if (now.after(user.getProfileexpiration())) {
            throw new UserProfileExpiredException(String.format("User [%s] has already expired.", user.getName()));
        }
        if (now.after(user.getPasswordexpiration())) {
            throw new CredentialExpiredException("Credentials for user " + user.getName() + " has already expired.");
        }
        if (!password.equals(this.getCryptography().decrypt(user.getPassword()))) {
            throw new InvalidUserException("Password for " + user.getName() + " is invalid.");
        }
        return user;
    }

}
