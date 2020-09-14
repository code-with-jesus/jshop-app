package com.jcode.jshop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jcode.jshop.backend.service.EmailService;
import com.jcode.jshop.web.domain.frontend.FeedbackPojo;

@Controller
public class ContactController {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
	
	/** The key that identifies the feedback payload in the Model. */
	private static final String FEEDBACK_MODEL_KEY = "feedback";
	
	/** The Contact Us view name. */
	private static final String CONTACT_US_VIEW_NAME = "contact/contact";
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/contact")
	public String contactGet(ModelMap model) {
		FeedbackPojo feedbackPojo = new FeedbackPojo();
		model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
		return ContactController.CONTACT_US_VIEW_NAME;
	}
	
	@PostMapping("/contact")
	public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedback) {
		LOG.debug("Feedback POJO content {}", feedback);
		emailService.sendFeedbackEmail(feedback);
		return ContactController.CONTACT_US_VIEW_NAME;
	}
}
