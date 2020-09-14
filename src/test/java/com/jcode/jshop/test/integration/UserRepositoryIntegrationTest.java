package com.jcode.jshop.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcode.jshop.backend.persistence.domain.backend.Plan;
import com.jcode.jshop.backend.persistence.domain.backend.Role;
import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.backend.persistence.domain.backend.UserRole;
import com.jcode.jshop.enums.PlansEnum;
import com.jcode.jshop.enums.RolesEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {
	
	@Rule
	public TestName testName = new TestName();

	@Before
	public void init() {
		assertNotNull(planRepository);
		assertNotNull(roleRepository);
		assertNotNull(userRepository);
	}
	
	@Test
	public void testCreateNewPlan() {
		Plan basicPlan = createPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);
		Plan retrievedPlan = planRepository.findById(PlansEnum.BASIC.getId()).get();
		assertNotNull(retrievedPlan);
	}
	
	@Test
	public void testCreateNewRole() {
		Role userRole = createRole(RolesEnum.BASIC);
		roleRepository.save(userRole);
		Role retrievedRole = roleRepository.findById(RolesEnum.BASIC.getId()).get();
		assertNotNull(retrievedRole);
	}
	
	@Test
	public void createNewUser() throws Exception {
		User basicUser = createUser(testName);
		User newlyCreatedUser = userRepository.findById(basicUser.getId()).get();
		
		assertNotNull(newlyCreatedUser);
		assertTrue(newlyCreatedUser.getId() != 0);
		assertNotNull(newlyCreatedUser.getPlan());
		assertNotNull(newlyCreatedUser.getPlan().getId());
		Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
		for (UserRole ur : newlyCreatedUserRoles) {
			assertNotNull(ur.getRole());
			assertNotNull(ur.getRole().getId());
		}
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		User basicUser = createUser(testName);
		userRepository.deleteById(basicUser.getId());
	}
	
	@Test
	public void testGetUserByEmail() throws Exception {
		User user = createUser(testName);
		
		User newlyFoundUser = userRepository.findByEmail(user.getEmail());
		assertNotNull(newlyFoundUser);
		assertNotNull(newlyFoundUser.getId());
	}
	
	@Test
	public void testUpdateUserPassword() throws Exception {
		User user = createUser(testName);
		assertNotNull(user);
		assertNotNull(user.getId());
		
		String newPassword = UUID.randomUUID().toString();
		userRepository.updateUserPassword(user.getId(), newPassword);
		
		user = userRepository.findById(user.getId()).get();
		assertEquals(newPassword, user.getPassword());
	}
	
}
