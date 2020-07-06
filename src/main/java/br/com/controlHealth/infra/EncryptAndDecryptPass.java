package br.com.controlHealth.infra;

import com.lambdaworks.crypto.SCryptUtil;

public class EncryptAndDecryptPass {
	
	private EncryptAndDecryptPass() {
		
	}
	
	public static String cryptPassword(String password) {
		return SCryptUtil.scrypt(password, 16, 16, 16);
	}
	
	public static boolean decryptPassword(String password, String passwordHashed) {
		return SCryptUtil.check(password, passwordHashed);
	}
	
}
