package framework.support.utilities;

import javax.inject.Inject;
import javax.inject.Named;

import org.jasypt.encryption.StringEncryptor;

import framework.core.utilities.Cryptography;

@Named
public class CryptographyImpl implements Cryptography {

    private final StringEncryptor encryptor;

    @Inject
    public CryptographyImpl(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /*
     * (non-Javadoc)
     * @see framework.support.utilities.ICryptography#decrypt(java.lang.String)
     */
    @Override
    public String decrypt(String encryptedMessage) {
        return this.encryptor.decrypt(encryptedMessage);
    }

    /*
     * (non-Javadoc)
     * @see framework.support.utilities.ICryptography#encrypt(java.lang.String)
     */
    @Override
    public String encrypt(String message) {
        return this.encryptor.encrypt(message);
    }

}
