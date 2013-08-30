package framework.core.utilities;

import junit.framework.Assert;

import org.junit.Test;

public class EncryptionUtilTest {

    @Test
    public void testCryptography() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        EncryptionUtil encryptionUtil = new EncryptionUtil(propertiesUtil);
        String encrypted = encryptionUtil.getEncryptedString("TEST");
        String decrypted = encryptionUtil.getDecryptedString(encrypted);
        Assert.assertEquals("TEST", decrypted);
        
        byte [] encryptedPassword = encryptionUtil.getEncryptedPassword("TEST");
        
        Assert.assertTrue(encryptionUtil.isEqual("TEST", encryptedPassword));
        Assert.assertFalse(encryptionUtil.isEqual("NOTEST", encryptedPassword));
    }
    
    @Test
    public void testSaltGenerator() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        EncryptionUtil encryptionUtil = new EncryptionUtil(propertiesUtil);
        
        String salt1 = encryptionUtil.getRandomSalt();
        String salt2 = encryptionUtil.getRandomSalt();
        
        Assert.assertNotSame(salt1, salt2);
    }
    
}
