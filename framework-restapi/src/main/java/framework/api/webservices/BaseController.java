package framework.api.webservices;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import framework.core.domain.user.User;

@Named
public abstract class BaseController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());
    private static final String MESSAGES = "messages";
    private static final long serialVersionUID = -1508227485108273495L;

    @Context
    private SecurityContext securityContext;

    protected BaseController() {

    }

    protected User getAuthenticatedUser() {
        final User user = (User) this.securityContext.getUserPrincipal();
        return user;
    }

    protected String getMessage(String key) {
        Locale locale = Locale.getDefault();
        if (this.getAuthenticatedUser() != null) {
            locale = new Locale(this.getAuthenticatedUser().getUserdetails().getLocale());
        }
        return this.getMessage(key, locale);
    }

    protected String getMessage(String key, Locale locale) {
        try {
            return ResourceBundle.getBundle(MESSAGES, locale).getString(key);
        } catch (final MissingResourceException e) {
            LOGGER.log(Level.WARNING, String.format("Missing key [%s] from resource.", key), e);
            return "";
        }
    }

}
