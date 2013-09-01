package framework.core.domain.user;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.EventType;
import framework.core.domain.ServiceImpl;
import framework.core.domain.auditlog.Auditlog;
import framework.core.domain.auditlog.AuditlogService;
import framework.core.domain.session.Session;
import framework.core.domain.session.SessionService;
import framework.core.exceptions.CredentialExpiredException;
import framework.core.exceptions.InvalidUserException;
import framework.core.exceptions.UserProfileExpiredException;
import framework.core.utilities.EncryptionUtil;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
class UserServiceImpl extends ServiceImpl<User> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;

    private final AuditlogService auditlogService;
    private final EncryptionUtil encryptionUtil;
    private final SessionService sessionService;
    private final UserDao userDao;

    @Inject
    protected UserServiceImpl(UserDao userDao, SessionService sessionService, AuditlogService auditlogService,
            EncryptionUtil encryptionUtil) {
        super(userDao);
        this.userDao = userDao;
        this.sessionService = sessionService;
        this.auditlogService = auditlogService;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public Session authenticate(String username, String password) {
        final User user = this.validateLogin(username, password);
        final Auditlog auditlog = new Auditlog();
        auditlog.setType(EventType.LOGIN);
        auditlog.setUserid(user.getId());
        this.auditlogService.saveOrUpdate(auditlog);
        return this.sessionService.saveOrUpdate(user);
    }
    
    @Override
    public User findUserByUsername(String username) {
        final List<User> users = this.userDao.findUsersByName(username);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void logout(User user) {
        final Auditlog auditlog = new Auditlog();
        auditlog.setType(EventType.LOGOUT);
        auditlog.setUserid(user.getId());
        this.auditlogService.saveOrUpdate(auditlog);
        this.sessionService.delete(user);
    }

    @Override
    public User saveOrUpdate(User user, String password) {
        String newPassword = encryptionUtil.getEncryptedPassword(password);
        user.setPassword(newPassword);
        return super.saveOrUpdate(user);
    }

    protected User validateLogin(String username, String password) {
        final User user = this.findUserByUsername(username);
        final Date now = Calendar.getInstance().getTime();
        if (user == null) {
            throw new InvalidUserException("Unable to find username matching [" + username + "]");
        }
        if (now.after(user.getProfileexpiration())) {
            throw new UserProfileExpiredException(String.format("User [%s] has already expired.", user.getName()));
        }
        if (now.after(user.getPasswordexpiration())) {
            throw new CredentialExpiredException("Credentials for user " + user.getName() + " has already expired.");
        }
        if (!this.encryptionUtil.isEqual(password, user.getPassword())) {
            throw new InvalidUserException("Password for " + user.getName() + " is invalid.");
        }
        return user;
    }

}
