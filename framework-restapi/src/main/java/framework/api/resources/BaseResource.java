package framework.api.resources;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import framework.core.domain.user.Credential;

@Named
public abstract class BaseResource implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BaseResource.class.getName());
    private static final String MESSAGES = "messages";
    private static final long serialVersionUID = -1508227485108273495L;

    @Context
    private SecurityContext securityContext;

    protected BaseResource() {

    }

    protected Credential getCredential() {
        final Credential user = (Credential) this.securityContext.getUserPrincipal();
        return user;
    }

    protected String getMessage(String key) {
        Locale locale = Locale.getDefault();
        if (this.getCredential() != null) {
            locale = new Locale(this.getCredential().getUser().getLocale());
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
