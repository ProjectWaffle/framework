package framework.core.utilities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Named;

import framework.core.exceptions.UtilityException;

@Named
public class EncryptionUtil {

    private final String asymmetricAlgorithm;
    private final int asymmetricKeylength;
    private final byte[] asymmetricSalt;
    private final Integer iterations;
    private final String symmetricAlgorithm;
    private final String symmetricPadding;
    private final byte[] symmetricSalt;

    @Inject
    protected EncryptionUtil(PropertiesUtil propertiesUtil) {
        try {
            this.iterations = propertiesUtil.getInteger(PropertiesUtil.ENCRYPTION_ITERATION);
            this.asymmetricSalt = propertiesUtil.get(PropertiesUtil.ENCRYPTION_ASYMMETRIC_SALT).getBytes("UTF-8");
            this.asymmetricAlgorithm = propertiesUtil.get(PropertiesUtil.ENCRYPTION_ASYMMETRIC_ALGORITHM);
            this.asymmetricKeylength = propertiesUtil.getInteger(PropertiesUtil.ENCRYPTION_ASYMMERTRIC_KEYLENGTH);
            this.symmetricAlgorithm = propertiesUtil.get(PropertiesUtil.ENCRYPTION_SYMMETRIC_ALGORITHM);
            this.symmetricSalt = propertiesUtil.get(PropertiesUtil.ENCRYPTION_SYMMETRIC_SALT).getBytes("UTF-8");
            this.symmetricPadding = propertiesUtil.get(PropertiesUtil.ENCRYPTION_SYMMETRIC_PADDING);
        } catch (final UnsupportedEncodingException e) {
            throw new UtilityException("Unable to instanstiate class.", e);
        }
    }

    @SuppressWarnings("restriction")
    public String getDecryptedString(String message) {
        try {
            final Cipher cipher = Cipher.getInstance(this.symmetricPadding);
            SecretKeySpec secretKey = new SecretKeySpec(this.symmetricSalt, this.symmetricAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decrypted = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(message)));
            final String salt = decrypted.split(";")[0];

            secretKey = new SecretKeySpec(salt.getBytes("UTF-8"), this.symmetricAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(decrypted.split(";")[1])));
        } catch (final Exception e) {
            throw new UtilityException("Encryption not possible.", e);
        }
    }

    public byte[] getEncryptedPassword(String password) {
        try {
            final KeySpec spec = new PBEKeySpec(password.toCharArray(), this.asymmetricSalt, this.iterations,
                    this.asymmetricKeylength);

            final SecretKeyFactory f = SecretKeyFactory.getInstance(this.asymmetricAlgorithm);

            return f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new UtilityException("Encryption not possible.", e);
        }
    }

    public String getEncryptedString(boolean value) {
        return this.getEncryptedString(String.valueOf(value));
    }

    public String getEncryptedString(int value) {
        return this.getEncryptedString(String.valueOf(value));
    }

    public String getEncryptedString(long value) {
        return this.getEncryptedString(String.valueOf(value));
    }

    @SuppressWarnings("restriction")
    public String getEncryptedString(String message) {
        try {
            final String salt = this.getRandomSalt();
            final Cipher cipher = Cipher.getInstance(this.symmetricPadding);
            SecretKeySpec secretKey = new SecretKeySpec(salt.getBytes("UTF-8"), this.symmetricAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encrypted = String.format("%s;%s", salt,
                    new sun.misc.BASE64Encoder().encode(cipher.doFinal(message.getBytes("UTF-8"))));

            secretKey = new SecretKeySpec(this.symmetricSalt, this.symmetricAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new sun.misc.BASE64Encoder().encode(cipher.doFinal(encrypted.getBytes("UTF-8")));
        } catch (final Exception e) {
            throw new UtilityException("Encryption not possible.", e);
        }
    }

    public String getRandomSalt() {
        final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        final StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            final SecureRandom random = new SecureRandom();
            salt.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return salt.toString();
    }

    public boolean isEqual(String attemptedPassword, byte[] encryptedPassword) {
        final byte[] encryptedAttemptedPassword = this.getEncryptedPassword(attemptedPassword);

        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

}