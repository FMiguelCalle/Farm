package com.fmcc.test.farm.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.controller.UserController;
import com.fmcc.farm.controller.UserControllerImpl;
import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.mappers.UserMapper;
import com.fmcc.farm.mappers.UserMapperImpl;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.UserService;
import com.fmcc.farm.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserController {
	
	private static final User USER = new User();
	private static final UserDTO USERDTO = new UserDTO();
	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private UserController userController = new UserControllerImpl();

	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserMapper userMapper = new UserMapperImpl();
	
	@Test
	public void testCreateOK() throws Exception {
		
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		USER.setUsername(USERNAME);
		USER.setId(ID);
		Mockito.when(userMapper.map(USERDTO)).thenReturn(USER);
		Mockito.when(userMapper.map(USER)).thenReturn(USERDTO);
		Mockito.when(userService.create(USER)).thenReturn(USER);
		
		final UserDTO u = userController.create(USERDTO);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertEquals(u.getUsername(), USERNAME);
	}
	
	@Test
	public void testCreateKO() throws Exception {
		
		USERDTO.setUsername(USERNAME);
		USER.setUsername(USERNAME);
		User u = new User();
		u.setUsername("KO");
		UserDTO uDTO = new UserDTO();
		uDTO.setUsername("KO");
		
		Mockito.when(userMapper.map(USERDTO)).thenReturn(USER);
		Mockito.when(userMapper.map(u)).thenReturn(uDTO);
		Mockito.when(userService.create(USER)).thenReturn(u);
		
		final UserDTO dto = userController.create(USERDTO);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getUsername(), USERNAME);
		
	}
	
	@Test
	public void testGetAllCondition1() throws Exception {
		USER.setId(ID);
		USER.setUsername(USERNAME);
		USERDTO.setId(ID);
		USERDTO.setUsername(USERNAME);
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(PAGE, SIZE)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(PAGE, SIZE);
		
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertTrue(PAGE > 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition2() throws Exception {
		final Integer page = 0;
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(page, SIZE)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(page, SIZE);
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertEquals(usersDTO.size(), 0);
		Assert.assertTrue(page <= 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition3() throws Exception {
		
		final Integer page = 0;
		final Integer size = 0;
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(page, size)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(page, size);
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertEquals(usersDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition4() throws Exception {
		final Integer page = 0;
		final Integer size = 20;
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(page, size)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(page, size);
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertEquals(usersDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size > 10 );
	}
	
	@Test
	public void testGetAllCondition5() throws Exception {
		
		final Integer size = 0;
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(PAGE, size)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(PAGE, size);
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertEquals(usersDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition6() throws Exception {
		
		final Integer size = 20;
		
		final List<User> users = new ArrayList<>();
		
		Mockito.when(userService.getAll(PAGE, size)).thenReturn(users);
		
		final List<UserDTO> usersDTO = userController.getAll(PAGE, size);
		
		Assert.assertNotNull(usersDTO);
		Assert.assertEquals(usersDTO.size(), users.size());
		Assert.assertEquals(usersDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size > 10 );
	}
	
	@Test
	public void testUpdateCondition1() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(ID);
		dto.setUsername(USERNAME);
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		final Integer id = ID;
		
		Mockito.when(userMapper.map(dto)).thenReturn(USER);
		
		userController.update(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(ID);
		dto.setUsername(USERNAME);
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		final Integer id = 23;
		
		Mockito.when(userMapper.map(dto)).thenReturn(USER);
		
		userController.update(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(null);
		
		final Integer id = ID;
		
		userController.update(dto, id);
		
		Assert.assertNull(dto.getId());
		
	}
	
	@Test
	public void testDeleteCondition1() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(ID);
		dto.setUsername(USERNAME);
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		final Integer id = ID;
		
		Mockito.when(userMapper.map(dto)).thenReturn(USER);
		
		userController.delete(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition2() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(ID);
		dto.setUsername(USERNAME);
		USER.setId(ID);
		USER.setUsername(USERNAME);
		
		final Integer id = 23;
		
		Mockito.when(userMapper.map(dto)).thenReturn(USER);
		
		userController.delete(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition3() throws Exception {
		
		UserDTO dto = new UserDTO();
		dto.setId(null);
		
		final Integer id = ID;
		
		userController.delete(dto, id);
		
		Assert.assertNull(dto.getId());
		
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		
		USER.setId(ID);
		USERDTO.setId(ID);
		USER.setUsername(USERNAME);
		USERDTO.setUsername(USERNAME);
		
		Mockito.when(userMapper.map(USER)).thenReturn(USERDTO);
		Mockito.when(userService.findById(ID)).thenReturn(USER);
		
		final UserDTO dto = userController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		USERDTO.setId(ID);
		
		User u = new User();
		u.setId(23);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setId(23);
		
		Mockito.when(userMapper.map(u)).thenReturn(uDTO);
		Mockito.when(userService.findById(ID)).thenReturn(u);
		
		final UserDTO dto = userController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
}
