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

import com.fmcc.farm.dao.CowDAO;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;
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
public class TestCowService {
	
	private static final Cow COW = new Cow();
	private static final User USER = new User();
	private static final String USERNAME = "ADMIN";
	private static final String BREED = "breed";
	private static final Integer ID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE-1, SIZE);
	
	@InjectMocks
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private CowDAO cowDAO;
	
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
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(cowDAO.save(cow)).thenReturn(COW);
		
		Cow c = cowService.create(cow, USERID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertEquals(c.getBreed(), cow.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), cow.getProductions());
		Assert.assertNotNull(c.getUserId());
		Assert.assertEquals(c.getUserId(), cow.getUserId());
		
	}
	
	@Test
	public void testCreateKO() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed("KO");
		cow.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(cowDAO.save(cow)).thenReturn(COW);
		
		Cow u = cowService.create(cow, USERID);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getBreed());
		Assert.assertNotNull(u.getProductions());
		Assert.assertTrue(!u.getProductions().equals(cow.getProductions()) ||
							!u.getBreed().equals(cow.getBreed()));
		
	}
	
	/*
	 * Condition 1:
	 * 		- user is not null.
	 * 		- page and size in range.
	 * 		- Empty List.
	 */
	@Test
	public void testGetAllCondition1() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(cowDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Cow>(new ArrayList<Cow>()));		
		
		List<Cow> cows = cowService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(cows);
		
	}
	
	/*
	 * Condition 2:
	 * 		- user is not null.
	 * 		- page and size in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testGetAllCondition2(){
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setUserId(USERID);
		
		List<Cow> cowList = new ArrayList<>();
		cowList.add(COW);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(cowDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Cow>(cowList));		
		
		List<Cow> cows = cowService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(cows);
	}
	
	/*
	 * Condition 3:
	 * 		- user is null.
	 * 		- page and size in range.
	 */
	@Test(expected=NullPointerException.class)
	public void testGetAllCondition3() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenThrow(new NullPointerException());
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		Mockito.when(cowDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Cow>(new ArrayList<Cow>()));		
		
		List<Cow> cows = cowService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNull(cows);
		
	}
	
	/*
	 * Condition 4:
	 * 		- user is not null.
	 * 		- page and size not in range.
	 */
	@Test
	public void testGetAllCondition4() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		COW.setUserId(USERID);
		
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		Mockito.when(userService.findById(USERID)).thenReturn(USER);
		
		List<Cow> cows = cowService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(cows);
		Assert.assertEquals(cows.size(), 0);
		
	}
	
	@Test
	public void testFindByIdOK() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setId(ID);
		
		Mockito.when(cowDAO.findOne(ID)).thenReturn(COW);
		
		Cow c = cowService.findById(ID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), cow.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertEquals(c.getBreed(), cow.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), cow.getProductions());
		
	}
	
	@Test
	public void testFindByIdKO() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Integer id = 23;
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed("KO");
		cow.setId(id);
		
		Mockito.when(cowDAO.findOne(id)).thenReturn(COW);
		
		Cow c = cowService.findById(id);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertTrue(!c.getProductions().equals(cow.getProductions()) ||
							!c.getBreed().equals(cow.getBreed()) ||
							!c.getId().equals(cow.getId()));
		
	}
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- user and cow are not null.
	 */
	@Test
	public void testUpdateCondition1() throws Exception {
		USER.setId(USERID);
		USER.setUsername(USERNAME);
		
		COW.setId(ID);
		COW.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(COW.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "cow")).thenReturn(true);
		
		cowService.update(COW, ID, USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- user or cow are null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		COW.setId(ID);
		COW.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(COW.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "cow")).thenThrow(new NullPointerException());
		
		cowService.update(COW, ID, USERID);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Ids not matching.
	 * 		- user and cow are not null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		COW.setId(23);
		COW.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(COW.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "cow")).thenReturn(true);
		
		cowService.update(COW, ID, USERID);
		
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching
	 * 		- user or cow are null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition4() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		COW.setId(23);
		COW.setUserId(USERID);
		
		Mockito.when(idValidator.validateMatchingIds(COW.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ID, "cow")).thenThrow(new NullPointerException());
		
		cowService.update(COW, ID, USERID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- cow is not null.
	 */
	@Test
	public void testAddNewProduction() throws Exception{
		COW.setId(ID);
		COW.setProductions(new ArrayList<>());
		COW.setUserId(USERID);
		
		Production production = new Production();
		production.setId(ID);
		production.setAnimalId(ID);
		production.setProductionDate(new Date());
		production.setSellingPrice(50);
		production.setPurchasePrice(25);
		
		Mockito.when(cowService.findById(ID)).thenReturn(COW);
		Mockito.when(notNullValidator.validateNotNull(COW)).thenReturn(true);
		
		cowService.addNewProduction(production, ID, USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- cow is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testAddNewProductionCondition2() throws Exception{
		Cow cow = null;
		
		Production production = new Production();
		production.setId(ID);
		production.setAnimalId(ID);
		production.setProductionDate(new Date());
		production.setSellingPrice(50);
		production.setPurchasePrice(25);
		
		Mockito.when(cowService.findById(ID)).thenReturn(cow);
		Mockito.when(notNullValidator.validateNotNull(cow)).thenThrow(new NullPointerException());
		
		cowService.addNewProduction(production, ID, USERID);
	}
	
	/*
	 * Condition 1:
	 * 		- cow is not null.
	 */
	@Test
	public void testFindByIdAndUserIdCondition1() {
		COW.setId(ID);
		COW.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(COW)).thenReturn(true);
		Mockito.when(cowDAO.findByIdAndUserId(ID, USERID)).thenReturn(COW);
		
		Cow cow = cowService.findByIdAndUserId(ID, USERID);
		
		Assert.assertNotNull(cow);
		Assert.assertEquals(cow.getId(), ID);
		Assert.assertEquals(cow.getUserId(), USERID);
	}
	
	/*
	 * Condition 2:
	 * 		- cow is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testFindByIdAndUserIdCondition2() {
		COW.setId(ID);
		COW.setUserId(USERID);
		
		Mockito.when(notNullValidator.validateNotNull(null)).thenThrow(new NullPointerException());
		Mockito.when(cowDAO.findByIdAndUserId(ID, USERID)).thenReturn(null);
		
		Cow cow = cowService.findByIdAndUserId(ID, USERID);
		
		Assert.assertNull(cow);
	}
}
