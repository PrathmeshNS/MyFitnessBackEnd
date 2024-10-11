package com.myFitness.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class EncryptPassword {
	public static String convertToMD5(String password) {
		try {
			if (password != null) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest = md.digest();
				StringBuilder sb = new StringBuilder();
				for (byte b : digest) {
					sb.append(String.format("%02x", b));
				}
				return sb.toString();
			}
			else {
				return "Password is Null";
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
