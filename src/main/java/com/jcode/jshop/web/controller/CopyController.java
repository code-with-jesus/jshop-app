package com.jcode.jshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CopyController {

	@GetMapping("/about")
	public String about() {
		return "copy/about";
	}
}
