package com.optum.pathway.poc.security;


import org.apache.logging.log4j.Logger;
import org.jasypt.encryption.StringEncryptor;

public class PathwayEncryptor implements StringEncryptor {
	private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PathwayEncryptor.class);

	@Override
	public String decrypt(String str) {
		try {
			return AESUtils.decryptText(str);
		} catch (Exception e) {
			logger.error("Error occurred in encryption", e);
			return null;
		}
	}

	@Override
	public String encrypt(String str) {
		try {
			return AESUtils.encryptText(str);
		} catch (Exception e) {
			logger.error("Error occurred in encryption", e);
			return null;
		}
	}
	public static void main(String ar[]){
		PathwayEncryptor pathwayEncryptor = new PathwayEncryptor();
		System.out.println(pathwayEncryptor.decrypt("F/K1z7tY4+TMowjuKj6jFQ=="));
	}


}
