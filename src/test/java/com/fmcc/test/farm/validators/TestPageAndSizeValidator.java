package com.fmcc.test.farm.validators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidatorImpl;


@RunWith(MockitoJUnitRunner.class)
public class TestPageAndSizeValidator {

	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private PageAndSizeValidator pageAndSizeValidator = new PageAndSizeValidatorImpl();

	/*
	 * Condition 1: page and size in range.
	 */
	@Test
	public void TestValidatePageAndSizeCondition1() {
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(PAGE, SIZE);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);
	}
	
	/*
	 * Condition 2: page in range, size <= 0.
	 */
	@Test
	public void TestValidatePageAndSizeCondition2() {
		
		final Integer size = 0;
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(PAGE, size);
		
		Assert.assertNotNull(res);
		Assert.assertFalse(res);
	}
	
	/*
	 * Condition 3: page in range, size > 10.
	 */
	@Test
	public void TestValidatePageAndSizeCondition3() {
		
		final Integer size = 23;
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(PAGE, size);
		
		Assert.assertNotNull(res);
		Assert.assertFalse(res);
	}
	
	/*
	 * Condition 4: page <= 0, size in range.
	 */
	@Test
	public void TestValidatePageAndSizeCondition4() {
		
		final Integer page = 0;
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(page, SIZE);
		
		Assert.assertNotNull(res);
		Assert.assertFalse(res);
	}
	
	/*
	 * Condition 5: page <=0, size <= 0.
	 */
	@Test
	public void TestValidatePageAndSizeCondition5() {
		
		final Integer size = 0;
		final Integer page = 0;
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(page, size);
		
		Assert.assertNotNull(res);
		Assert.assertFalse(res);
	}
	
	/*
	 * Condition 6: page <= 0, size > 10.
	 */
	@Test
	public void TestValidatePageAndSizeCondition6() {
		
		final Integer size = 23;
		final Integer page = 0;
		
		Boolean res = pageAndSizeValidator.validatePageAndSize(page, size);
		
		Assert.assertNotNull(res);
		Assert.assertFalse(res);
	}
}
