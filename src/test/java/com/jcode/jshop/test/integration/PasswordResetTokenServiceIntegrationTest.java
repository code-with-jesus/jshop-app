package com.jcode.jshop.test.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcode.jshop.backend.persistence.domain.backend.PasswordResetToken;
import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.backend.service.PasswordResetTokenService;

public class PasswordResetTokenServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Rule
	private TestName testName = new TestName();
	
	@Test
	public void testCreateNewTokenForUserEmail() throws Exception {
		User user = createUser(testName);
		PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
		
		assertNotNull(passwordResetToken);
		assertNotNull(passwordResetToken.getToken());
	}
	
	@Test
	public void testFindByToken() throws Exception {
		User user = createUser(testName);
		PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
		
		assertNotNull(passwordResetToken);
		assertNotNull(passwordResetToken.getToken());
		
		PasswordResetToken token = passwordResetTokenService.findByToken(passwordResetToken.getToken());
		assertNotNull(token);
	}
}
