package com.pranshusaini.chatapp.utils;

import static com.pranshusaini.chatapp.utils.ConfigReader.getValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException{
		String encryptedPassword = null;
		
		// We will convert the plain text to encrypted text using inbuilt library.
		MessageDigest messageDigest = MessageDigest.getInstance(getValue("ENCRYPTALGORITHM"));
		messageDigest.update(plainPassword.getBytes());
		byte[] encrypt = messageDigest.digest();
		
//		Converting the encrypted bytes
		StringBuffer sb = new StringBuffer();
		for(byte b : encrypt) {
			sb.append(b);
		}
		encryptedPassword = sb.toString();
		
		return encryptedPassword;
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		System.out.println(passwordEncrypt("amit"));
//	}
}
