package com.jcode.jshop;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcode.jshop.backend.persistence.domain.backend.Role;
import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.backend.persistence.domain.backend.UserRole;
import com.jcode.jshop.backend.service.PlanService;
import com.jcode.jshop.backend.service.UserService;
import com.jcode.jshop.enums.PlansEnum;
import com.jcode.jshop.enums.RolesEnum;
import com.jcode.jshop.utils.UserUtils;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanService planService;
	
	@Value("${webmaster.username}")
	private String webmasterUsername;
	
	@Value("${webmaster.password}")
	private String webmasterPassword;
	
	@Value("${webmaster.email}")
	private String webmasterEmail;
	
	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOG.info("Creating Basic and Pro plans in the database...");
		planService.createPlan(PlansEnum.BASIC.getId());
		planService.createPlan(PlansEnum.PRO.getId());
		
		User user = UserUtils.createBasicUser(webmasterUsername, webmasterEmail);
		user.setPassword(webmasterPassword);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.ADMIN)));
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("User {} created", user.getUsername());
	}
}
