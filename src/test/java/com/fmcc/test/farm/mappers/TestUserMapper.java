package com.fmcc.test.farm.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.mappers.user.UserMapper;
import com.fmcc.farm.mappers.user.UserMapperImpl;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {
	
	private static final User USER = new User();
	private static final UserDTO USERDTO = new UserDTO();
	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;
	
	@InjectMocks
	private UserMapper userMapper = new UserMapperImpl();
	
	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Test
	public void testUserToDTOMapOKEmptyAnimals() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		USER.setAnimals(new ArrayList<>());
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		USERDTO.setAnimals(new ArrayList<>());
		
		Mockito.when(mapper.map(USER, UserDTO.class)).thenReturn(USERDTO);
		
		final UserDTO dto = userMapper.map(USER);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), USER.getId());
		Assert.assertNotNull(dto.getUsername());
		Assert.assertEquals(dto.getUsername(), USER.getUsername());
	}
	
	@Test
	public void testUserToDTOMapOKNotEmptyAnimals() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		List<Animal> animals = new ArrayList<>();
		Chicken chicken = new Chicken();
		Cow cow = new Cow();
		chicken.setId(1);
		chicken.setUserId(ID);
		cow.setId(2);
		cow.setUserId(ID);
		animals.add(chicken);
		animals.add(cow);
		USER.setAnimals(animals);
		
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		List<Integer> animalsId = new ArrayList<>();
		animalsId.add(1);
		animalsId.add(2);
		USERDTO.setAnimals(animalsId);
		
		Mockito.when(mapper.map(USER, UserDTO.class)).thenReturn(USERDTO);
		
		final UserDTO dto = userMapper.map(USER);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), USER.getId());
		Assert.assertNotNull(dto.getUsername());
		Assert.assertEquals(dto.getUsername(), USER.getUsername());
		Assert.assertEquals(dto.getAnimals().size(), animals.size());
	}
	
	@Test
	public void testUserToDTOMapKO() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		
		UserDTO uDTO = new UserDTO();
		uDTO.setId(23);
		uDTO.setUsername("KO");
		
		Mockito.when(mapper.map(USER, UserDTO.class)).thenReturn(uDTO);
		
		final UserDTO dto = userMapper.map(USER);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), USER.getId());
		Assert.assertTrue(!dto.getId().equals(USER.getId()) ||
				!dto.getUsername().equals(USER.getUsername()));
	}
	
	@Test
	public void testDTOToUserMapOKEmptyAnimals() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		USER.setAnimals(new ArrayList<>());
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		USERDTO.setAnimals(new ArrayList<>());
		
		Mockito.when(mapper.map(USERDTO, User.class)).thenReturn(USER);
		
		final User user = userMapper.map(USERDTO);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user.getId(), USERDTO.getId());
		Assert.assertNotNull(user.getUsername());
		Assert.assertEquals(user.getUsername(), USERDTO.getUsername());
	}
	
	@Test
	public void testDTOToUserMapOKNotEmptyAnimals() {
		USER.setUsername(USERNAME);
		USER.setId(ID);
		List<Animal> animals = new ArrayList<>();
		Chicken chicken = new Chicken();
		Cow cow = new Cow();
		chicken.setId(1);
		chicken.setUserId(ID);
		cow.setId(2);
		cow.setUserId(ID);
		animals.add(chicken);
		animals.add(cow);
		USER.setAnimals(animals);
		
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		List<Integer> animalsId = new ArrayList<>();
		animalsId.add(1);
		animalsId.add(2);
		USERDTO.setAnimals(animalsId);
		
		Mockito.when(chickenService.findById(1)).thenReturn(chicken);
		Mockito.when(chickenService.findById(2)).thenReturn(null);
		Mockito.when(cowService.findById(1)).thenReturn(null);
		Mockito.when(cowService.findById(2)).thenReturn(cow);
		Mockito.when(mapper.map(USERDTO, User.class)).thenReturn(USER);
		
		final User user = userMapper.map(USERDTO);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user.getId(), USERDTO.getId());
		Assert.assertNotNull(user.getUsername());
		Assert.assertEquals(user.getUsername(), USERDTO.getUsername());
		Assert.assertEquals(user.getAnimals().size(), animalsId.size());
	}
	
	@Test
	public void testDTOToUserMapKO() {
		USERDTO.setUsername(USERNAME);
		USERDTO.setId(ID);
		USERDTO.setAnimals(new ArrayList<>());
		
		User u = new User();
		u.setId(23);
		u.setUsername("KO");
		u.setAnimals(new ArrayList<>());
		
		Mockito.when(mapper.map(USERDTO, User.class)).thenReturn(u);
		
		final User user = userMapper.map(USERDTO);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertNotEquals(user.getId(), USERDTO.getId());
		Assert.assertTrue(!user.getId().equals(USERDTO.getId()) ||
				!user.getUsername().equals(USERDTO.getUsername()));
	}
	
}
