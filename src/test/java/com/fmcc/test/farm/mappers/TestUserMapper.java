package com.fmcc.test.farm.mappers;

import java.util.ArrayList;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.mappers.AnimalMapper;
import com.fmcc.farm.mappers.AnimalMapperImpl;
import com.fmcc.farm.mappers.UserMapper;
import com.fmcc.farm.mappers.UserMapperImpl;
import com.fmcc.farm.model.User;

@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {
	
	private static final User USER = new User();
	private static final UserDTO USERDTO = new UserDTO();
	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;
	
	@InjectMocks
	private UserMapper userMapper = new UserMapperImpl();
	
	@Mock
	private AnimalMapper animalMapper = new AnimalMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Test
	public void testUserToDTOMapOK() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		
		Mockito.when(mapper.map(USER, UserDTO.class)).thenReturn(USERDTO);
		Mockito.when(animalMapper.createListIdFromAnimal(USER.getAnimals())).thenReturn(new ArrayList<>());

		
		final UserDTO dto = userMapper.map(USER);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), USER.getId());
		Assert.assertNotNull(dto.getUsername());
		Assert.assertEquals(dto.getUsername(), USER.getUsername());
	}
	
	@Test
	public void testUserToDTOMapKO() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setId(23);
		uDTO.setUsername("KO");
		
		Mockito.when(mapper.map(USER, UserDTO.class)).thenReturn(uDTO);
		Mockito.when(animalMapper.createListIdFromAnimal(USER.getAnimals())).thenReturn(new ArrayList<>());

		
		final UserDTO dto = userMapper.map(USER);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), USER.getId());
		Assert.assertTrue(!dto.getId().equals(USER.getId()) ||
				!dto.getUsername().equals(USER.getUsername()));
	}
	
	@Test
	public void testDTOToUserMapOK() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		
		Mockito.when(mapper.map(USERDTO, User.class)).thenReturn(USER);
		Mockito.when(animalMapper.createListAnimalFromId(USERDTO.getAnimals())).thenReturn(new ArrayList<>());

		
		final User user = userMapper.map(USERDTO);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user.getId(), USERDTO.getId());
		Assert.assertNotNull(user.getUsername());
		Assert.assertEquals(user.getUsername(), USERDTO.getUsername());
	}
	
	@Test
	public void testDTOToUserMapKO() {
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		
		User u = new User();
		u.setId(23);
		u.setUsername("KO");
		
		Mockito.when(mapper.map(USERDTO, User.class)).thenReturn(u);
		Mockito.when(animalMapper.createListAnimalFromId(USERDTO.getAnimals())).thenReturn(new ArrayList<>());

		
		final User user = userMapper.map(USERDTO);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertNotEquals(user.getId(), USERDTO.getId());
		Assert.assertTrue(!user.getId().equals(USERDTO.getId()) ||
				!user.getUsername().equals(USERDTO.getUsername()));
	}
	
}
