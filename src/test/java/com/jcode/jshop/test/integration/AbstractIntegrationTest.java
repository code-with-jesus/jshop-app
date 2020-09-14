package com.jcode.jshop.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcode.jshop.backend.persistence.domain.backend.Plan;
import com.jcode.jshop.backend.persistence.domain.backend.Role;
import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.backend.persistence.domain.backend.UserRole;
import com.jcode.jshop.backend.persistence.repositories.PlanRepository;
import com.jcode.jshop.backend.persistence.repositories.RoleRepository;
import com.jcode.jshop.backend.persistence.repositories.UserRepository;
import com.jcode.jshop.enums.PlansEnum;
import com.jcode.jshop.enums.RolesEnum;
import com.jcode.jshop.utils.UserUtils;

public abstract class AbstractIntegrationTest {

	@Autowired
	protected PlanRepository planRepository;

	@Autowired
	protected RoleRepository roleRepository;

	@Autowired
	protected UserRepository userRepository;


	protected Plan createPlan(PlansEnum plansENum) {
		return new Plan(plansENum);
	}

	protected Role createRole(RolesEnum rolesEnum) {
		return new Role(rolesEnum);
	}

	protected User createUser(String username, String email) {
		Plan basicPlan = createPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);

		User basicUser = UserUtils.createBasicUser(username, email);
		basicUser.setPlan(basicPlan);

		Role basicRole = new Role(RolesEnum.BASIC);
		roleRepository.save(basicRole);

		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);

		basicUser.getUserRoles().addAll(userRoles);
		basicUser = userRepository.save(basicUser);
		return basicUser;
	}
	
	protected User createUser(TestName testName) {
		return createUser(testName.getMethodName(), testName.getMethodName() + "@devopsbuddy.com");
	}
}
