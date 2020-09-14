package com.jcode.jshop.backend.service;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcode.jshop.backend.persistence.domain.backend.Plan;
import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.backend.persistence.domain.backend.UserRole;
import com.jcode.jshop.backend.persistence.repositories.PlanRepository;
import com.jcode.jshop.backend.persistence.repositories.RoleRepository;
import com.jcode.jshop.backend.persistence.repositories.UserRepository;
import com.jcode.jshop.enums.PlansEnum;

@Service
@Transactional(readOnly = true)
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
		
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		
		Plan plan = new Plan(plansEnum);
		// It makes sure the plans exists in the database
		if (!planRepository.existsById(plansEnum.getId())) {
			planRepository.save(plan);
		}
		user.setPlan(plan);
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		// flush referenced objects before the user is saved
		em.flush();
		user = userRepository.save(user);
		return user;
	}
	
	/**
	 * Returns a user by username or null if a user could not be found.
	 * @param username The username to be found.
	 * @return A user by username or null if a user could not be found.
	 */
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * Returns a user for the given email or null if a user could not be found.
	 * @param email The email associated to the user to find.
	 * @return A user for the given email or null if a user could not be found.
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Transactional
	public void updateUserPassword(long userId, String password) {
		password = passwordEncoder.encode(password);
		userRepository.updateUserPassword(userId, password);
		LOG.debug("Password updated successfully for user id {}", userId);
	}
	
}
