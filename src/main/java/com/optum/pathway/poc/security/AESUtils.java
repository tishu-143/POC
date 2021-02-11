package com.optum.pathway.poc.security;

import com.optum.pathway.poc.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

/**
 * Security Util for protecting property file passwords
 *
 */

public class AESUtils {

	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(AESUtils.class);
	private static final String ERROR_MSG = "External Properties File not found. Make sure this JVM argument is set -DEXTERNALIZED_PROPERTIES_FILE=-DEXTERNALIZED_PROPERTIES_FILE=/ebiz/app_logs/externalized-properties.properties";
	public static final String PROP_NAME_CRA_ENCRYPTION = "pathway.security.encryption.key";
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String decryptedString;
	private static String encryptedString;
	private static String encryptionKey;
	private static IvParameterSpec ivspec;
	private static Properties props = FileUtils.getExternalizedProperties();

	private AESUtils(){
		throw new IllegalStateException("Utility class, illegal state");
	}

	// validate and set encryption key if it is null.
	public static void setEncryptionKey(String paramEncryptionKey) {
		if (encryptionKey == null) {
			if (StringUtils.isBlank(paramEncryptionKey)) {
				throw new IllegalArgumentException(
						"CRA property encryption key not found. Make sure the property "
								+ PROP_NAME_CRA_ENCRYPTION
								+ " is set in external file with JVM argument EXTERNALIZED_PROPERTIES_FILE.");
			}
			encryptionKey = paramEncryptionKey.trim();
		}
	}

	public static void setKey() throws GeneralSecurityException{
		MessageDigest sha = null;
		key = encryptionKey.getBytes(StandardCharsets.UTF_8);
		sha = MessageDigest.getInstance("SHA-512");
		key = sha.digest(key);
		// use 32 byte for AES 256 bit key encryption
		key = Arrays.copyOf(key, 32);
		secretKey = new SecretKeySpec(key, "AES");
		byte[] ivBytes = Arrays.copyOf(key, 16);
		ivspec = new IvParameterSpec(ivBytes);
	}

	public static String getDecryptedString() {
		return decryptedString;
	}

	public static void setDecryptedString(String decryptedString) {
		AESUtils.decryptedString = decryptedString;
	}

	public static String getEncryptedString() {
		return encryptedString;
	}

	public static void setEncryptedString(String encryptedString) {
		AESUtils.encryptedString = encryptedString;
	}

	/**
	 * @param strToEncrypt
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static void encrypt(String strToEncrypt)
			throws GeneralSecurityException {
		setKey();

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

		setEncryptedString(Base64.getEncoder().encodeToString(cipher
				.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8))));
	}

	/**
	 * @param strToDecrypt
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static void decrypt(String strToDecrypt)
			throws GeneralSecurityException {

		// set the encryption key
		setKey();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		setDecryptedString(new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt.getBytes(StandardCharsets.UTF_8))),
				StandardCharsets.UTF_8));
	}

	

	/**
	 * @param args
	 * @throws IOException
	 */
	public static String decryptText(String strToEncryptOrDecrypt) {

		String encryptionKey = getEncryptKey();

		// set the encryption key
		setEncryptionKey(encryptionKey);

			try {
				AESUtils.decrypt(strToEncryptOrDecrypt.trim());
			} catch (GeneralSecurityException e) {
				logger.error("Exception occurred during decryption:",e);
			}
		return AESUtils.getDecryptedString();
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static String encryptText(String strToEncryptOrDecrypt)  {

		String encryptionKey = getEncryptKey();

		// set the encryption key
		setEncryptionKey(encryptionKey);

			try {
				AESUtils.encrypt(strToEncryptOrDecrypt.trim());
			} catch (GeneralSecurityException e) {
				logger.error("Exception occurred during encryption:",e);
			}
		return AESUtils.getEncryptedString();
	}
	
	private static String getEncryptKey() {
		String encryptionKey = null;
		if (props == null) {
			logger.error(ERROR_MSG);
			throw new IllegalStateException(ERROR_MSG);
		}
		

		if (props.getProperty(PROP_NAME_CRA_ENCRYPTION) != null) {
			encryptionKey = props.getProperty(PROP_NAME_CRA_ENCRYPTION);
		}
		return encryptionKey;
	}



	
}