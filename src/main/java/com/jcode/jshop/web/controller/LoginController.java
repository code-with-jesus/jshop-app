package com.jcode.jshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	/** The login view name */
	public static final String LOGIN_VIEW_NAME = "user/login";
	
	@GetMapping("/login")
	public String login() {
		return LOGIN_VIEW_NAME;
	}
}
