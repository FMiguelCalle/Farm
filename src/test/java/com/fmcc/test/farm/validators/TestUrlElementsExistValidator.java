package com.fmcc.test.farm.validators;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.production.ProductionServiceImpl;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.notnull.NotNullValidatorImpl;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUrlElementsExistValidator {

	private static final Integer USERID = 1;
	private static final Integer ANIMALID = 1;
	private static final Integer PRODUCTIONID = 1;
	private static final String ANIMALTYPECHICKEN = "chicken";
	private static final String ANIMALTYPECOW = "cow";
	private static final User USER = new User();
	private static final Chicken CHICKEN = new Chicken();
	private static final Cow COW = new Cow();
	private static final Production PRODUCTION = new Production();
	
	@InjectMocks
	private UrlElementsExistValidator validator = new UrlElementsExistValidatorImpl();

	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private ProductionService productionService = new ProductionServiceImpl();
	
	@Mock
	private NotNullValidator notNullValidator = new NotNullValidatorImpl();
	
	/*
	 * Condition 1: 
	 * 		- user is not null.
	 * 		- animal type is chicken.
	 * 		- chicken is not null. 		
	 */
	@Test
	public void TestValidateUrlElementsExistenceCondition1() throws Exception {
		USER.setId(USERID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername("ADMIN");
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(chickenService.findByIdAndUserId(USERID, ANIMALID)).thenReturn(CHICKEN);
		Mockito.when(notNullValidator.validateNotNull(CHICKEN)).thenReturn(true);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);
	}
	
	/*
	 * Condition 2: 
	 * 		- user is null.	
	 */
	@Test(expected=NullPointerException.class)
	public void TestValidateUrlElementsExistenceCondition2() throws Exception{
		User user = null;
		
		Mockito.when(userService.findById(USERID)).thenReturn(user);
		Mockito.when(notNullValidator.validateNotNull(user)).thenThrow(new NullPointerException());
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, "animalType");
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 3: 
	 * 		- user is not null.
	 * 		- animal type is chicken.
	 * 		- chicken is null. 		
	 */
	@Test
	public void TestValidateUrlElementsExistenceCondition3() throws Exception {
		USER.setId(USERID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername("ADMIN");
		
		Chicken chicken = null;
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(chickenService.findByIdAndUserId(USERID, ANIMALID)).thenReturn(chicken);
		Mockito.when(notNullValidator.validateNotNull(chicken)).thenThrow(new NullPointerException());
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		
		Assert.assertNull(res);
	}
}
