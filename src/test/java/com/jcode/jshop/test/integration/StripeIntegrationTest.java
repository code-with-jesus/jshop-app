package com.jcode.jshop.test.integration;

import static org.junit.Assert.assertNotNull;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcode.jshop.backend.service.StripeService;
import com.jcode.jshop.enums.PlansEnum;
import com.stripe.Stripe;
import com.stripe.model.Customer;

public class StripeIntegrationTest {

	public static final String TEST_CC_NUMBER = "4242424242424242";

	public static final Integer TEST_CC_EXP_MONTH = 1;

	public static final String TEST_CC_CVC_NBR = "314";

	@Autowired
	private StripeService stripeService;

	@Autowired
	private String stripeKey;

	@Before
	public void init() {
		assertNotNull(stripeKey);
		Stripe.apiKey = stripeKey;
	}
	
	@Test
	public void createStripeCustomer() throws Exception {
		Map<String, Object> tokenParams = new HashMap<>();
		Map<String, Object> cardParams = new HashMap<>();
		cardParams.put("number", TEST_CC_NUMBER);
		cardParams.put("exp_month", TEST_CC_EXP_MONTH);
		cardParams.put("exp_year", LocalDate.now(Clock.systemUTC()).getYear() + 1);
		cardParams.put("cvc", TEST_CC_CVC_NBR);
		tokenParams.put("card", cardParams);
		
		Map<String, Object> customerParams = new HashMap<>();
		customerParams.put("description", "Customer for test@example.com");
		customerParams.put("plan", PlansEnum.PRO.getId());
		
		String stripeCustomerId = stripeService.createCustomer(tokenParams, customerParams);
		assertNotNull(stripeCustomerId);
		
		Customer cu = Customer.retrieve(stripeCustomerId);
		cu.delete();
	}
}
