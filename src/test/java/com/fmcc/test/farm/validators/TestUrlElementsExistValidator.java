package com.fmcc.test.farm.validators;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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
	
	@Spy
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
	public void testValidateUrlElementsExistenceCondition1() throws Exception {
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
	public void testValidateUrlElementsExistenceCondition2() throws Exception{
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
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition3() throws Exception {
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
	
	/*
	 * Condition 4:
	 * 		- user is not null.
	 * 		- animal type is cow.
	 * 		- cow is not null.
	 */
	@Test
	public void testValidateUrlElementsExistenceCondition4() throws Exception {
		USER.setId(USERID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername("ADMIN");
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(cowService.findByIdAndUserId(USERID, ANIMALID)).thenReturn(COW);
		Mockito.when(notNullValidator.validateNotNull(COW)).thenReturn(true);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECOW);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);
	}
	
	/*
	 * Condition 5:
	 * 		- user is not null.
	 * 		- animal type is cow.
	 * 		- cow is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition5() throws Exception {
		USER.setId(USERID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername("ADMIN");
		
		Cow cow = null;
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(cowService.findByIdAndUserId(USERID, ANIMALID)).thenReturn(cow);
		Mockito.when(notNullValidator.validateNotNull(cow)).thenThrow(new NullPointerException());
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 6:
	 * 		- user is not null.
	 * 		- animal type is not cow or chicken.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition6() throws Exception {
		USER.setId(USERID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername("ADMIN");
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, "Platypus");
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 7:
	 * 		- validate userId, animalId and animalType ok.
	 * 		- production is not null.
	 */
	@Test
	public void testValidateUrlElementsExistenceCondition7() throws Exception {
		PRODUCTION.setId(PRODUCTIONID);
		PRODUCTION.setAnimalId(ANIMALID);
			
		Mockito.doReturn(true).when(validator).validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenReturn(true);
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(PRODUCTIONID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN, PRODUCTIONID);
		
		Assert.assertNotNull(res);
		Assert.assertTrue(res);
	}
	
	/*
	 * Condition 8:
	 * 		- validate userId, animalId and animalType ok.
	 * 		- production is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition8() throws Exception {
		PRODUCTION.setId(PRODUCTIONID);
		PRODUCTION.setAnimalId(ANIMALID);
			
		Mockito.doReturn(true).when(validator).validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenThrow(new NullPointerException());
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(PRODUCTIONID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN, PRODUCTIONID);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 9:
	 * 		- validate userId, animalId and animalType not ok.
	 * 		- production is not null.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition9() throws Exception {
		PRODUCTION.setId(PRODUCTIONID);
		PRODUCTION.setAnimalId(ANIMALID);
			
		Mockito.doThrow(new NullPointerException()).when(validator).validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenReturn(true);
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(PRODUCTIONID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN, PRODUCTIONID);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 10:
	 * 		- validate userId, animalId and animalType not ok.
	 * 		- production is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition10() throws Exception {
		PRODUCTION.setId(PRODUCTIONID);
		PRODUCTION.setAnimalId(ANIMALID);
			
		Mockito.doThrow(new NullPointerException()).when(validator).validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenThrow(new NullPointerException());
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(PRODUCTIONID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		Boolean res = validator.validateUrlElementsExistence(USERID, ANIMALID, ANIMALTYPECHICKEN, PRODUCTIONID);
		
		Assert.assertNull(res);
	}
	
	/*
	 * Condition 11:
	 * 		- user is not null.
	 */
	@Test
	public void testValidateUrlElementsExistenceCondition11() throws Exception {
		USER.setId(USERID);
		USER.setUsername("ADMIN");
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		
		Boolean res = validator.validateUrlElementsExistence(USERID);
		
		Assert.assertNotNull(res);
	}
	
	/*
	 * Condition 12:
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testValidateUrlElementsExistenceCondition12() throws Exception {
		USER.setId(USERID);
		USER.setUsername("ADMIN");
		
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenThrow(new NullPointerException());
		
		Boolean res = validator.validateUrlElementsExistence(USERID);
		
		Assert.assertNull(res);
	}
}
