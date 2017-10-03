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
		COWDTO.setBreed("retinto");
		COW.setId(ID);
		COW.setBreed("retinto");
		
		CowDTO dto = new CowDTO();
		dto.setBreed("breed");
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		Mockito.when(cowService.create(COW)).thenReturn(COW);
		
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
		Mockito.when(cowService.create(COW)).thenReturn(COW);
		
		final CowDTO dto = cowController.create(cDTO, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
	}
	
	@Test
	public void testGetAllCondition1() throws Exception {
		COW.setId(ID);
		COWDTO.setId(ID);
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, PAGE, SIZE)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertTrue(PAGE > 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition2() throws Exception {
		final Integer page = 0;
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, page, SIZE)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(page, SIZE, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition3() throws Exception {
		
		final Integer page = 0;
		final Integer size = 0;
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, page, size)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(page, size, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition4() throws Exception {
		final Integer page = 0;
		final Integer size = 20;
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, page, size)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(page, size, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size > 10 );
	}
	
	@Test
	public void testGetAllCondition5() throws Exception {
		
		final Integer size = 0;
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, PAGE, size)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition6() throws Exception {
		
		final Integer size = 20;
		
		final List<Cow> cows = new ArrayList<>();
		
		Mockito.when(cowService.getAll(USERID, PAGE, size)).thenReturn(cows);
		
		final List<CowDTO> cowsDTO = cowController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(cowsDTO);
		Assert.assertEquals(cowsDTO.size(), cows.size());
		Assert.assertEquals(cowsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size > 10 );
	}
	
	@Test
	public void testUpdateCondition1() throws Exception {
		
		CowDTO dto = new CowDTO();
		dto.setId(ID);
		COW.setId(ID);
		
		final Integer id = ID;
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		
		cowController.update(dto, USERID, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		
		CowDTO dto = new CowDTO();
		dto.setId(ID);
		COW.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(cowMapper.map(dto)).thenReturn(COW);
		
		cowController.update(dto, USERID, id);;
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		
		CowDTO dto = new CowDTO();
		dto.setId(null);
		
		final Integer id = ID;
		cowController.update(dto, USERID, id);
		Assert.assertNull(dto.getId());
		
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		COWDTO.setId(ID);
		COW.setId(ID);
		COWDTO.setBreed("breed");
		COW.setBreed("breed");
		
		Mockito.when(cowMapper.map(COW)).thenReturn(COWDTO);
		Mockito.when(cowService.findById(ID)).thenReturn(COW);
		
		final CowDTO dto = cowController.findById(ID);
		
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
		Mockito.when(cowService.findById(ID)).thenReturn(c);
		
		final CowDTO dto = cowController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
}
