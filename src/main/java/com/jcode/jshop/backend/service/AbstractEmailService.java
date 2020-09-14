package com.jcode.jshop.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jcode.jshop.web.domain.frontend.FeedbackPojo;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.to.address}")
	private String defaultToAdderss;
	/**
	 * Create a Simple Mail Message from a Feedback Pojo.
	 * @param feedback The Feedback pojo
	 * @return
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(defaultToAdderss);
		message.setFrom(feedback.getEmail());
		message.setSubject("[DevOps Buddy] Feedback received from " + feedback.getFirstName() + " " + feedback.getLastName() + "!");
		message.setText(feedback.getFeedback());
		return message;
	}
	
	@Override
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
		sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
	}
}
