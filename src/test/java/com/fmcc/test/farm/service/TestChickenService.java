package com.fmcc.test.farm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fmcc.farm.dao.ChickenDAO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidatorImpl;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.notnull.NotNullValidatorImpl;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidatorImpl;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestChickenService {
	
	private static final Chicken CHICKEN = new Chicken();
	private static final User USER = new User();
	private static final String USERNAME = "ADMIN";
	private static final String FRECUENCY = "frecuency";
	private static final String TYPE = "tipo";
	private static final Integer ID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE-1, SIZE);
	
	@InjectMocks
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private ChickenDAO chickenDAO;
	
	@Mock
	private PageAndSizeValidator pageAndSizeValidator = new PageAndSizeValidatorImpl();
	
	@Mock
	private PathIdAndDTOIdMatchValidator idValidator = new PathIdAndDTOIdMatchValidatorImpl();
	
	@Mock
	private NotNullValidator notNullValidator = new NotNullValidatorImpl();
	
	@Mock
	private UrlElementsExistValidator urlElementsValidator = new UrlElementsExistValidatorImpl();
	
	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Test
	public void testCreateOK() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(chickenDAO.save(chicken)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.create(chicken, USERID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertEquals(c.getFrecuency(), chicken.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertEquals(c.getType(), chicken.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), chicken.getProductions());
		Assert.assertNotNull(c.getUserId());
		Assert.assertEquals(c.getUserId(), chicken.getUserId());
		
	}
	
	@Test
	public void testCreateKO() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency("KO");
		chicken.setType("KO");
		chicken.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		
		Chicken u = chickenService.create(chicken, USERID);
		
		Assert.assertNull(u);
		
	}
	
	/*
	 * Condition 1:
	 * 		- user is not null.
	 * 		- page and size in range.
	 * 		- Empty List.
	 */
	@Test
	public void testGetAllCondition1() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(chickenDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Chicken>(new ArrayList<Chicken>()));		
		
		List<Chicken> chickens = chickenService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(chickens);
		
	}
	
	/*
	 * Condition 2:
	 * 		- user is not null.
	 * 		- page and size in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testGetAllCondition2(){
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setUserId(USERID);
		
		List<Chicken> chickenList = new ArrayList<>();
		chickenList.add(CHICKEN);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(chickenDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Chicken>(chickenList));		
		
		List<Chicken> chickens = chickenService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(chickens);
	}
	
	/*
	 * Condition 3:
	 * 		- user is null.
	 * 		- page and size in range.
	 */
	@Test(expected=NullPointerException.class)
	public void testGetAllCondition3() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenThrow(new NullPointerException());
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(chickenDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Chicken>(new ArrayList<Chicken>()));		
		
		List<Chicken> chickens = chickenService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNull(chickens);
		
	}
	
	/*
	 * Condition 4:
	 * 		- user is not null.
	 * 		- page and size not in range.
	 */
	@Test
	public void testGetAllCondition4() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		
		List<Chicken> chickens = chickenService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(chickens);
		Assert.assertEquals(chickens.size(), 0);
		
	}
	
	@Test
	public void testFindByIdOK() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setId(ID);
		
		Mockito.when(chickenDAO.findOne(ID)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.findById(ID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), chicken.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertEquals(c.getFrecuency(), chicken.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertEquals(c.getType(), chicken.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), chicken.getProductions());
		
	}
	
	@Test
	public void testFindByIdKO() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Integer id = 23;
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency("KO");
		chicken.setType("KO");
		chicken.setId(id);
		
		Mockito.when(chickenDAO.findOne(id)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.findById(id);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertTrue(!c.getProductions().equals(chicken.getProductions()) ||
							!c.getFrecuency().equals(chicken.getFrecuency()) ||
							!c.getType().equals(chicken.getType()) ||
							!c.getId().equals(chicken.getId()));
		
	}
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- user and chicken are not null.
	 */
	@Test
	public void testUpdateCondition1() throws Exception {
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(CHICKEN.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "chicken")).thenReturn(true);
		
		chickenService.update(CHICKEN, ID, USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- user or chicken are null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(CHICKEN.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "chicken")).thenThrow(new NullPointerException());
		
		chickenService.update(CHICKEN, ID, USERID);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Ids not matching.
	 * 		- user and chicken are not null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		CHICKEN.setId(23);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(CHICKEN.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "chicken")).thenReturn(true);
		
		chickenService.update(CHICKEN, ID, USERID);
		
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching
	 * 		- user or chicken are null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition4() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		CHICKEN.setId(23);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(CHICKEN.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "chicken")).thenThrow(new NullPointerException());
		
		chickenService.update(CHICKEN, ID, USERID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- chicken is not null.
	 */
	@Test
	public void testAddNewProduction() throws Exception{
		CHICKEN.setId(ID);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setUserId(USERID);
		
		Production production = new Production();
		production.setId(ID);
		production.setAnimalId(ID);
		production.setProductionDate(new Date());
		production.setSellingPrice(50);
		production.setPurchasePrice(25);
		
		Mockito.when(chickenService.findById(ID)).thenReturn(CHICKEN);
		Mockito.when(notNullValidator.validateNotNull(CHICKEN)).thenReturn(true);
		
		chickenService.addNewProduction(production, ID, USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- chicken is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testAddNewProductionCondition2() throws Exception{
		Chicken chicken = null;
		
		Production production = new Production();
		production.setId(ID);
		production.setAnimalId(ID);
		production.setProductionDate(new Date());
		production.setSellingPrice(50);
		production.setPurchasePrice(25);
		
		Mockito.when(chickenService.findById(ID)).thenReturn(chicken);
		Mockito.when(notNullValidator.validateNotNull(chicken)).thenThrow(new NullPointerException());
		
		chickenService.addNewProduction(production, ID, USERID);
	}
	
	/*
	 * Condition 1:
	 * 		- chicken is not null.
	 */
	@Test
	public void testFindByIdAndUserIdCondition1() {
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(CHICKEN)).thenReturn(true);
		Mockito.when(chickenDAO.findByIdAndUserId(ID, USERID)).thenReturn(CHICKEN);
		
		Chicken chicken = chickenService.findByIdAndUserId(ID, USERID);
		
		Assert.assertNotNull(chicken);
		Assert.assertEquals(chicken.getId(), ID);
		Assert.assertEquals(chicken.getUserId(), USERID);
	}
	
	/*
	 * Condition 2:
	 * 		- chicken is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testFindByIdAndUserIdCondition2() {
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(null)).thenThrow(new NullPointerException());
		Mockito.when(chickenDAO.findByIdAndUserId(ID, USERID)).thenReturn(null);
		
		Chicken chicken = chickenService.findByIdAndUserId(ID, USERID);
		
		Assert.assertNull(chicken);
	}
}
