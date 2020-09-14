package com.jcode.jshop.web.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class I18NService {
	
	/** The application logger */
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(I18NService.class);

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Returns a message for a given message id and the default locale in the session context
	 * @param messageId The key to the messages resource file
	 */
	public String getMessage(String messageId) {
		LOG.info("Returning i18n text from messageId {}", messageId);
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(messageId, locale);
	}
	
	/**
	 * Returns a message for a given message id and locale
	 * @param messageId The key to the messages resource file
	 * @param locale The locale
	 */
	public String getMessage(String messageId, Locale locale) {
		return messageSource.getMessage(messageId, null, locale);
	}
}
