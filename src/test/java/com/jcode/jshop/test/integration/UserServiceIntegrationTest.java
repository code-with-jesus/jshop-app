package com.jcode.jshop.test.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcode.jshop.backend.persistence.domain.backend.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void testCreateNewUser() {
		User user = createUser(testName);
		assertNotNull(user);
		assertNotNull(user.getId());
	}
}
