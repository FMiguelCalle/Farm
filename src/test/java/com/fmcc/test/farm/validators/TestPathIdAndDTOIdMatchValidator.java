package com.fmcc.test.farm.validators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestPathIdAndDTOIdMatchValidator {
	
	private static final Integer PATHID = 1;
	private static final Integer DTOID = 1;

	@InjectMocks
	private PathIdAndDTOIdMatchValidator idValidator = new PathIdAndDTOIdMatchValidatorImpl();
	
	/*
	 * Condition 1: DTO id and Path id are not null and both are equal.
	 */
	@Test
	public void TestvalidateMatchingIdCondition1() throws Exception{
		
		Boolean res = idValidator.validateMatchingIds(DTOID, PATHID);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);	
	}
	
	/*
	 * Condition 2: DTO id and Path id are not null but both are not equal.
	 */
	@Test(expected=NullPointerException.class)
	public void TestValidateMatchingIdCondition2() throws Exception {
		
		final Integer pathId = 23;
		
		Boolean res = idValidator.validateMatchingIds(DTOID, pathId);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 3: Path id is null.
	 */
	@Test(expected=NullPointerException.class)
	public void TestValidateMatchingIdCondition3() throws Exception {
		
		final Integer pathId = null;
		
		Boolean res = idValidator.validateMatchingIds(DTOID, pathId);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 4: DTO id is null.
	 */
	@Test(expected=NullPointerException.class)
	public void TestValidateMatchingIdCondition4() throws Exception {
		
		final Integer dtoId = null;
		
		Boolean res = idValidator.validateMatchingIds(dtoId, PATHID);
		
		Assert.assertNull(res);
	}
}
