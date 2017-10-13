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

import com.fmcc.farm.controller.useranimals.UserAnimalsController;
import com.fmcc.farm.controller.useranimals.UserAnimalsControllerImpl;
import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.dto.UserAnimalsDTO;
import com.fmcc.farm.mappers.chicken.ChickenMapper;
import com.fmcc.farm.mappers.chicken.ChickenMapperImpl;
import com.fmcc.farm.mappers.cow.CowMapper;
import com.fmcc.farm.mappers.cow.CowMapperImpl;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserAnimalsController {
	
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private UserAnimalsController userAnimalsController = new UserAnimalsControllerImpl();

	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private ChickenMapper chickenMapper = new ChickenMapperImpl();
	
	@Mock
	private CowMapper cowMapper = new CowMapperImpl();
	
	@Test
	public void testGetAllOKEmptyList() throws Exception {
		
		List<Chicken> chickens = new ArrayList<>();
		List<Cow> cows = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		
		UserAnimalsDTO dtos = userAnimalsController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.getChickens());
		Assert.assertNotNull(dtos.getCows());
	}
	
	@Test
	public void testGetAllOKNotEmptyList() throws Exception {
		
		List<Chicken> chickens = new ArrayList<>();
		Chicken chicken = new Chicken();
		chicken.setId(23);
		chicken.setUserId(USERID);
		chickens.add(chicken);
		List<Cow> cows = new ArrayList<>();
		Cow cow = new Cow();
		cow.setId(10);
		cow.setUserId(USERID);
		cows.add(cow);
		ChickenDTO chickenDTO = new ChickenDTO();
		chickenDTO.setId(23);
		CowDTO cowDTO = new CowDTO();
		cowDTO.setId(10);
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		Mockito.when(chickenMapper.map(chicken)).thenReturn(chickenDTO);
		Mockito.when(cowMapper.map(cow)).thenReturn(cowDTO);
		
		UserAnimalsDTO dtos = userAnimalsController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.getChickens());
		Assert.assertNotNull(dtos.getCows());
		Assert.assertNotNull(dtos.getChickens().get(0));
		Assert.assertNotNull(dtos.getCows().get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetAllKONull() throws Exception {
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(null);
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(null);
		
		UserAnimalsDTO dtos = userAnimalsController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(dtos);
		Assert.assertNull(dtos.getChickens());
		Assert.assertNull(dtos.getCows());
	}
}
