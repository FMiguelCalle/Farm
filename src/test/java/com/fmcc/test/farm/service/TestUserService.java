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

import com.fmcc.farm.dao.UserDAO;
import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidatorImpl;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.notnull.NotNullValidatorImpl;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	private static final User USER = new User();
	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE-1, SIZE);
	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private PageAndSizeValidator pageAndSizeValidator = new PageAndSizeValidatorImpl();
	
	@Mock
	private PathIdAndDTOIdMatchValidator idValidator = new PathIdAndDTOIdMatchValidatorImpl();
	
	@Mock
	private NotNullValidator notNullValidator = new NotNullValidatorImpl();
	
	@Test
	public void testCreateOK() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		
		Mockito.when(userDAO.save(user)).thenReturn(USER);
		
		User u = userService.create(user);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getUsername());
		Assert.assertEquals(u.getUsername(), user.getUsername());
		Assert.assertNotNull(u.getAnimals());
		Assert.assertEquals(u.getAnimals(), user.getAnimals());
		
	}
	
	@Test
	public void testCreateKO() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername("KO");
		
		Mockito.when(userDAO.save(user)).thenReturn(USER);
		
		User u = userService.create(user);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getUsername());
		Assert.assertNotNull(u.getAnimals());
		Assert.assertTrue(!u.getAnimals().equals(user.getAnimals()) ||
							!u.getUsername().equals(user.getUsername()));
		
	}
	
	/*
	 * Condition 1:
	 * 		- Page and size in range.
	 * 		- Empty List.
	 */
	@Test
	public void testGetAllCondition1() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userDAO.findAll(PAGEABLE)).thenReturn(new PageImpl<User>(new ArrayList<User>()));		
		List<User> users = userService.getAll(PAGE, SIZE);
		
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 0);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Page or size out of range.
	 * 		- Empty List
	 */
	@Test
	public void testGetAllCondition2() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		Mockito.when(userDAO.findAll(PAGEABLE)).thenReturn(new PageImpl<User>(new ArrayList<User>()));		
		List<User> users = userService.getAll(PAGE, SIZE);
		
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 0);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Page and size in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testGetAllCondition3() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		List<User> preUsers = new ArrayList<>();
		preUsers.add(USER);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userDAO.findAll(PAGEABLE)).thenReturn(new PageImpl<User>(preUsers));		
		List<User> users = userService.getAll(PAGE, SIZE);
		
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size()>0);
		
	}
	
	/*
	 * Condition 4:
	 * 		- Page or size out of range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testGetAllCondition4() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		Mockito.when(userDAO.findAll(PAGEABLE)).thenReturn(new PageImpl<User>(new ArrayList<User>()));		
		List<User> users = userService.getAll(PAGE, SIZE);
		
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 0);
		
	}
	
	@Test
	public void testFindByIdOK() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		user.setId(ID);
		
		Mockito.when(userDAO.findOne(ID)).thenReturn(USER);
		
		User u = userService.findById(ID);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertEquals(u.getId(), user.getId());
		Assert.assertNotNull(u.getUsername());
		Assert.assertEquals(u.getUsername(), user.getUsername());
		Assert.assertNotNull(u.getAnimals());
		Assert.assertEquals(u.getAnimals(), user.getAnimals());
		
	}
	
	@Test
	public void testFindByIdKO() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		Integer id = 23;
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername("KO");
		user.setId(id);
		
		Mockito.when(userDAO.findOne(id)).thenReturn(USER);
		
		User u = userService.findById(id);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getUsername());
		Assert.assertNotNull(u.getAnimals());
		Assert.assertTrue(!u.getAnimals().equals(user.getAnimals()) ||
							!u.getUsername().equals(user.getUsername()) ||
							!u.getId().equals(user.getId()));
		
	}
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- user is not null.
	 */
	@Test
	public void testDeleteCondition1() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenReturn(true);
		Mockito.when(notNullValidator.validateNotNull(userService.findById(ID))).thenReturn(true);
		
		userService.delete(USER, ID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition2() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenReturn(true);
		Mockito.when(notNullValidator.validateNotNull(userService.findById(ID))).thenThrow(new NullPointerException());
		
		userService.delete(USER, ID);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Ids not matching.
	 * 		- user is not null.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition3() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(notNullValidator.validateNotNull(userService.findById(ID))).thenReturn(true);
		
		userService.delete(USER, ID);
		
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition4() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(notNullValidator.validateNotNull(userService.findById(ID))).thenThrow(new NullPointerException());
		
		userService.delete(USER, ID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- user is not null.
	 */
	@Test
	public void testUpdateCondition1() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenReturn(true);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		
		userService.update(USER, ID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenReturn(true);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenThrow(new NullPointerException());
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		
		userService.update(USER, ID);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Ids not matching.
	 * 		- user is not null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		
		userService.update(USER, ID);
		
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition4() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		Mockito.when(idValidator.validateMatchingIds(USER.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(notNullValidator.validateNotNull(USER)).thenThrow(new NullPointerException());
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		
		userService.update(USER, ID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- user is not null.
	 */
	@Test
	public void testAddNewAnimal() throws Exception{
		USER.setId(ID);
		USER.setAnimals(new ArrayList<>());
		USER.setUsername(USERNAME);
		
		Chicken chicken = new Chicken();
		chicken.setId(1);
		chicken.setUserId(ID);
		
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		Mockito.when(notNullValidator.validateNotNull(USER)).thenReturn(true);
		
		userService.addNewAnimal(chicken, ID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- user is null.
	 */
	@Test(expected=NullPointerException.class)
	public void testAddNewAnimalCondition2() throws Exception{
		User user = null;
		
		Chicken chicken = new Chicken();
		chicken.setId(1);
		chicken.setUserId(ID);
		
		Mockito.when(userService.findById(ID)).thenReturn(user);
		Mockito.when(notNullValidator.validateNotNull(user)).thenThrow(new NullPointerException());
		
		userService.addNewAnimal(chicken, ID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- page and size in range.
	 *		- Empty List.
	 */
	@Test
	public void testUsersEarningsBetweenDatesCondition1() throws Exception {
		Date fromDate = new Date(1506336868069L);
		Date toDate = new Date(1506406868069L);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userDAO.usersEarningsBetweenDates(fromDate, toDate, PAGEABLE)).thenReturn(new PageImpl<StatsUserEarningDTO>(new ArrayList<>()));
		
		List<StatsUserEarningDTO> res = userService.usersEarningsBetweenDates(fromDate, toDate, PAGE, SIZE);
		
		Assert.assertNotNull(res);
	}
	
	/*
	 * Condition 2:
	 * 		- page and size not in range.
	 * 		- Empty List.
	 */
	@Test
	public void testUsersEarningsBetweenDatesCondition2() throws Exception {
		Date fromDate = new Date(1506336868069L);
		Date toDate = new Date(1506406868069L);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		
		List<StatsUserEarningDTO> res = userService.usersEarningsBetweenDates(fromDate, toDate, PAGE, SIZE);
		
		Assert.assertNotNull(res);
	}
	
	/*
	 * Condition 3:
	 * 		- page and size in range.
	 *		- Not Empty List.
	 */
	@Test
	public void testUsersEarningsBetweenDatesCondition3() throws Exception {
		Date fromDate = new Date(1506336868069L);
		Date toDate = new Date(1506406868069L);
		
		List<StatsUserEarningDTO> dtos = new ArrayList<>();
		StatsUserEarningDTO dto = new StatsUserEarningDTO();
		dto.setId(ID);
		dto.setEarning(123L);
		dtos.add(dto);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(userDAO.usersEarningsBetweenDates(fromDate, toDate, PAGEABLE)).thenReturn(new PageImpl<StatsUserEarningDTO>(dtos));
		
		List<StatsUserEarningDTO> res = userService.usersEarningsBetweenDates(fromDate, toDate, PAGE, SIZE);
		
		Assert.assertNotNull(res);
		Assert.assertNotNull(res.get(0));
	}
	
	/*
	 * Condition 4:
	 * 		- page and size not in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testUsersEarningsBetweenDatesCondition4() throws Exception {
		Date fromDate = new Date(1506336868069L);
		Date toDate = new Date(1506406868069L);
		
		Mockito.when(pageAndSizeValidator.validatePageAndSize(0, SIZE)).thenReturn(false);
		
		List<StatsUserEarningDTO> res = userService.usersEarningsBetweenDates(fromDate, toDate, PAGE, SIZE);
		
		Assert.assertNotNull(res);
	}
}
