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

import com.fmcc.farm.controller.cow.CowController;
import com.fmcc.farm.controller.cow.CowControllerImpl;
import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.mappers.cow.CowMapper;
import com.fmcc.farm.mappers.cow.CowMapperImpl;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestCowController {
	
	private static final Cow COW = new Cow();
	private static final CowDTO COWDTO = new CowDTO();
	private static final Integer USERID = 1;
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private CowController cowController = new CowControllerImpl();

	@Mock
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private CowMapper cowMapper = new CowMapperImpl();
	
	@Test
	public void testCreateOK() throws Exception {
		COWDTO.setId(ID);
		COWDTO.setBreed("breed");
		COW.setId(ID);
		COW.setBreed("breed");
		
		CowDTO dto = new CowDTO();
		dto.setBreed("breed");
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		Mockito.when(cowService.create(COW, USERID)).thenReturn(COW);
		
		final CowDTO c = cowController.create(dto, USERID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getBreed(), COWDTO.getBreed());
	}
	
	@Test
	public void testCreateKO() throws Exception {
		
		COWDTO.setId(23);
		COW.setId(ID);
		CowDTO cDTO = new CowDTO();
		
		Mockito.when(cowMapper.map(cDTO)).thenReturn(COW);
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		Mockito.when(cowService.create(COW,USERID)).thenReturn(COW);
		
		final CowDTO dto = cowController.create(cDTO, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
	}
	
	@Test
	public void testGetAllOKEmptyList() throws Exception {
		COW.setId(ID);
		COWDTO.setId(ID);
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
	}
	
	@Test
	public void testGetAllOKNotEmptyList() throws Exception {
		COW.setId(ID);
		COW.setUserId(USERID);
		COW.setBreed("BREED");
		COW.setProductions(new ArrayList<>());
		COWDTO.setId(ID);
		COWDTO.setBreed("BREED");
		COWDTO.setProductions(new ArrayList<>());
		
		final List<Cow> cows = new ArrayList<>();		
		cows.add(COW);
		
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.get(0).getId(), COW.getId());
		Assert.assertEquals(cowsDTO.get(0).getBreed(), COW.getBreed());
		Assert.assertEquals(cowsDTO.get(0).getProductions().size(), COW.getProductions().size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetAllKONull() throws Exception {
		
		final List<Cow> cows = null;
		
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNull(cowsDTO);
	}
	
	@Test
	public void testUpdateOK() throws Exception {
		
		CowDTO dto = new CowDTO();
		dto.setId(ID);
		COW.setId(ID);
		COW.setUserId(USERID);
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		
		cowController.update(dto, USERID, ID);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateKO() throws Exception {
		
		CowDTO dto = new CowDTO();
		dto.setId(ID);
		COW.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		Mockito.doThrow(NullPointerException.class).when(cowService).update(COW, id, USERID);
			
		cowController.update(dto, USERID, id);
		
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		COWDTO.setId(ID);
		COWDTO.setBreed("BREED");
		COW.setId(ID);		
		COW.setBreed("BREED");
		
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		Mockito.when(cowService.findByIdAndUserId(ID, USERID)).thenReturn(COW);
		
		final CowDTO dto = cowController.findById(ID,USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		COWDTO.setId(ID);
		
		Cow c = new Cow();
		c.setId(23);
		
		CowDTO cDTO = new CowDTO();
		cDTO.setId(23);
		
		Mockito.when(cowMapper.map(c)).thenReturn(cDTO);
		Mockito.when(cowService.findByIdAndUserId(ID, USERID)).thenReturn(c);
		
		final CowDTO dto = cowController.findById(ID,USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}	
}
