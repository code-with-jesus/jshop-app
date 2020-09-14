package com.jcode.jshop.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.jcode.jshop.backend.service.EmailService;
import com.jcode.jshop.backend.service.SmtpEmailService;

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/jshop/application-prod.properties")
public class ProductionConfig {

	@Value("${stripe.prod.private.key}")
	private String stripeProdKey;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	@Bean
	public String stripeKey() {
		return stripeProdKey;
	}
}
