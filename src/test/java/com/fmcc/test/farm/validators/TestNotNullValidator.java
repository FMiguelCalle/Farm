package com.fmcc.test.farm.validators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.notnull.NotNullValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestNotNullValidator {
	
	private static final Object OBJECT = new Object();
	
	@InjectMocks
	private NotNullValidator notNullValidator = new NotNullValidatorImpl();
	
	/*
	 * Condition 1: Object not null.
	 */
	@Test
	public void TestValidateNotNullCondition1() throws Exception{
		
		Boolean res = notNullValidator.validateNotNull(OBJECT);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);	
	}
	
	/*
	 * Condition 2: Object null.
	 */
	@Test(expected=NullPointerException.class)
	public void TestValidateMatchingIdCondition2() throws Exception {
		
		final Object o = null;
		
		Boolean res = notNullValidator.validateNotNull(o);
		
		Assert.assertNull(res);
	}
}
