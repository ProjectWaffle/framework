package framework.core.utilities;

public interface Cryptography {

    String decrypt(String encryptedMessage);

    String encrypt(String message);

}