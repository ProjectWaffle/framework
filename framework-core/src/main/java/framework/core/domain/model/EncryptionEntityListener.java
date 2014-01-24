package framework.core.domain.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import framework.core.exceptions.PersistenceNotPossibleException;
import framework.core.utilities.EncryptionUtil;

public class EncryptionEntityListener implements Serializable {

    private static final long serialVersionUID = 1590891613808450608L;
    private final EncryptionUtil encryptionUtil = EncryptionUtil.getInstance();

    @PostLoad
    protected void onLoad(BaseEntity entity) {
        try {
            final Field[] fields = entity.getClass().getDeclaredFields();
            for (final Field field : fields) {
                final Encrypted encrypted = field.getAnnotation(Encrypted.class);
                if (encrypted != null) {
                    field.setAccessible(true);
                    final String value = String.valueOf(field.get(entity));
                    field.set(entity, this.encryptionUtil.getDecryptedString(value));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new PersistenceNotPossibleException("Unable to persist record(s).", e);
        }
    }

    @PrePersist
    @PreUpdate
    protected void onSaveOrUpdate(BaseEntity entity) {
        try {
            final Field[] fields = entity.getClass().getDeclaredFields();
            for (final Field field : fields) {
                final Encrypted encrypted = field.getAnnotation(Encrypted.class);
                if (encrypted != null) {
                    field.setAccessible(true);
                    final String value = String.valueOf(field.get(entity));
                    field.set(entity, this.encryptionUtil.getEncryptedString(value));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new PersistenceNotPossibleException("Unable to persist record(s).", e);
        }
    }
}
