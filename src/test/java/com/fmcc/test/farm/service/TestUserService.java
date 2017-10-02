package com.fmcc.test.farm.service;

import java.util.ArrayList;
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
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.UserService;
import com.fmcc.farm.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	private static final User USER = new User();
	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE, SIZE);
	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserDAO userDAO;
	
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
	
	@Test
	public void testGetAllOK() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername(USERNAME);
		
		Mockito.when(userDAO.findAll(PAGEABLE)).thenReturn(new PageImpl<User>(new ArrayList<User>()));		
		List<User> users = userService.getAll(PAGE, SIZE);
		
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() <= SIZE);
		
	}
	
	@Test
	public void testGetAllKO() {
		USER.setUsername(USERNAME);
		USER.setAnimals(new ArrayList<>());
		USER.setId(ID);
		
		User user = new User();
		user.setAnimals(new ArrayList<>());
		user.setUsername("KO");
		
		List<User> aux = new ArrayList<>();
		aux.add(USER);
		aux.add(user);
		
		Integer page = 0;
		Integer size = 1;
		
		Mockito.when(userDAO.findAll(new PageRequest(page,size))).thenReturn(new PageImpl<User>(aux));		
		List<User> users = userService.getAll(page, size);
		
		Assert.assertNotNull(users);
		Assert.assertFalse(users.size() <= size );		
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
}
