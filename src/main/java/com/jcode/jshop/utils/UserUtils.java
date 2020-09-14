package com.jcode.jshop.utils;

import javax.servlet.http.HttpServletRequest;

import com.jcode.jshop.backend.persistence.domain.backend.User;
import com.jcode.jshop.web.controller.ForgotMyPasswordController;
import com.jcode.jshop.web.domain.frontend.BasicAccountPayload;

public class UserUtils {

	private UserUtils() {
		throw new AssertionError("Non instantiable");
	}

	/**
	 * Creates a user with basic attributes set.
	 * 
	 * @param username The username,
	 * @param email    The email.
	 * @return A User entity
	 */
	public static User createBasicUser(String username, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword("secret");
		user.setEmail(email);
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPhoneNumber("123456789123");
		user.setCountry("GB");
		user.setEnabled(true);
		user.setDescription("A basic user");
		user.setProfileImageUrl("https://blabla.images.com/basicuser");
		user.setStripeCustomerId("stripeCustomerId");
		return user;
	}

	public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
		String passwordResetUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + ForgotMyPasswordController.CHANGE_PASSWORD_PATH + "?id=" + userId
				+ "&token=" + token;
		return passwordResetUrl;
	}

	public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T frontendPayload) {
		User user = new User();
		user.setUsername(frontendPayload.getUsername());
		user.setPassword(frontendPayload.getPassword());
		user.setFirstName(frontendPayload.getFirstName());
		user.setLastName(frontendPayload.getLastName());
		user.setEmail(frontendPayload.getEmail());
		user.setPhoneNumber(frontendPayload.getPhoneNumber());
		user.setCountry(frontendPayload.getCountry());
		user.setDescription(frontendPayload.getDescription());
		user.setEnabled(true);
		return user;
	}
}
