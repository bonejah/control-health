package br.com.controlHealth.infra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EncryptAndDecryptPassTest {
	
	@Test
	public void shouldBeCryptAndDecryptAPassword() {
		String password="12345";
//		System.out.println("Value password: " + password);
		
		String passwordCrypted = EncryptAndDecryptPass.cryptPassword(password);
//		System.out.println(passwordCrypted);
		
		boolean matched = EncryptAndDecryptPass.decryptPassword(password, passwordCrypted);
//		System.out.println("Value matched: " + matched);
		assertEquals(matched, true);
	}
	
}
