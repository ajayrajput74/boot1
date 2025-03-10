package com.oauth.config;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AesUtil {

	private static final    Logger logger = LoggerFactory.getLogger(AesUtil.class);

	private final int keySize;
	private final int iterationCount;
	private final Cipher cipher;

	public AesUtil(int keySize, int iterationCount) {
		this.keySize = keySize;
		this.iterationCount = iterationCount;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		}
		catch (Exception  e) {
			logger.error(e.getMessage());
			throw fail(e);
		}
	}

	public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
			return new String(decrypted, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {

			logger.error(e.getMessage());
			return null;
		}catch (Exception e){

			logger.error(e.getMessage());
			return null;
		}
	}

	private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
		try {
			cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
			return cipher.doFinal(bytes);
		}
		catch (InvalidKeyException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException
				| BadPaddingException e) {

			logger.error(e.getMessage());
			return null;
		}
	}

	private SecretKey generateKey(String salt, String passphrase) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
			SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return key;
		}
		catch (Exception  e) {

			logger.error(e.getMessage());
			return null;
		}
	}

	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}

	public static byte[] hex(String str) {
		try {
			return Hex.decodeHex(str.toCharArray());
		}
		catch (DecoderException e) {
			logger.error( e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	private IllegalStateException fail(Exception e) {
		return null;
	}
}
