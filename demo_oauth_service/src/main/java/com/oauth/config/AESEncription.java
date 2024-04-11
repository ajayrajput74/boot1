package com.oauth.config;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncription {

	private static final String ALGO = "AES";
    private static final byte[] keyValue =
           new byte[]{'A','J','A','Y','R','A','J','P','U','T'};
	
    
    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
       // return new BASE64Encoder().encode(encVal);
        
        return Base64.getEncoder().encodeToString(encVal);
    }
    
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
       //byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }
    
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }
	
}
