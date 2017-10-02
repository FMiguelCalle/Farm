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

import com.fmcc.farm.controller.ChickenController;
import com.fmcc.farm.controller.ChickenControllerImpl;
import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.mappers.ChickenMapper;
import com.fmcc.farm.mappers.ChickenMapperImpl;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.service.ChickenService;
import com.fmcc.farm.service.ChickenServiceImpl;
import com.fmcc.farm.service.UserService;
import com.fmcc.farm.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestChickenController {
	
	private static final Chicken CHICKEN = new Chicken();
	private static final ChickenDTO CHICKENDTO = new ChickenDTO();
	private static final Integer USERID = 1;
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private ChickenController chickenController = new ChickenControllerImpl();

	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private ChickenMapper chickenMapper = new ChickenMapperImpl();
	
	@Test
	public void testCreateOK() throws Exception {
		CHICKENDTO.setId(ID);
		CHICKENDTO.setFrecuency("frecuencia");
		CHICKENDTO.setType("tipo");
		CHICKEN.setId(ID);
		CHICKEN.setFrecuency("frecuencia");
		CHICKEN.setType("tipo");
		
		ChickenDTO dto = new ChickenDTO();
		dto.setFrecuency("frecuencia");
		dto.setType("tipo");
		
		Mockito.when(chickenMapper.map(dto)).thenReturn(CHICKEN);
		Mockito.when(chickenMapper.map(CHICKEN)).thenReturn(CHICKENDTO);
		Mockito.when(chickenService.create(CHICKEN)).thenReturn(CHICKEN);
		
		final ChickenDTO c = chickenController.create(dto, USERID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getFrecuency(), CHICKENDTO.getFrecuency());
		Assert.assertEquals(c.getType(), CHICKENDTO.getType());
	}
	
	@Test
	public void testCreateKO() throws Exception {
		
		CHICKENDTO.setId(23);
		CHICKEN.setId(ID);
		ChickenDTO cDTO = new ChickenDTO();
		
		Mockito.when(chickenMapper.map(cDTO)).thenReturn(CHICKEN);
		Mockito.when(chickenMapper.map(CHICKEN)).thenReturn(CHICKENDTO);
		Mockito.when(chickenService.create(CHICKEN)).thenReturn(CHICKEN);
		
		final ChickenDTO dto = chickenController.create(cDTO, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
	}
	
	@Test
	public void testGetAllCondition1() throws Exception {
		CHICKEN.setId(ID);
		CHICKENDTO.setId(ID);
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertTrue(PAGE > 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition2() throws Exception {
		final Integer page = 0;
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, page, SIZE)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(page, SIZE, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.size(), 0);
		Assert.assertTrue(page <= 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition3() throws Exception {
		
		final Integer page = 0;
		final Integer size = 0;
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, page, size)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(page, size, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition4() throws Exception {
		final Integer page = 0;
		final Integer size = 20;
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, page, size)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(page, size, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size > 10 );
	}
	
	@Test
	public void testGetAllCondition5() throws Exception {
		
		final Integer size = 0;
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, PAGE, size)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition6() throws Exception {
		
		final Integer size = 20;
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, PAGE, size)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size > 10 );
	}
	
	@Test
	public void testUpdateCondition1() throws Exception {
		
		ChickenDTO dto = new ChickenDTO();
		dto.setId(ID);
		CHICKEN.setId(ID);
		
		final Integer id = ID;
		
		Mockito.when(chickenMapper.map(dto)).thenReturn(CHICKEN);
		
		chickenController.update(dto, USERID, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2() throws Exception {
		
		ChickenDTO dto = new ChickenDTO();
		dto.setId(ID);
		CHICKEN.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(chickenMapper.map(dto)).thenReturn(CHICKEN);
		
		chickenController.update(dto, USERID, id);;
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		
		ChickenDTO dto = new ChickenDTO();
		dto.setId(null);
		
		final Integer id = ID;
		chickenController.update(dto, USERID, id);
		Assert.assertNull(dto.getId());
		
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		CHICKENDTO.setId(ID);
		CHICKENDTO.setFrecuency("frecuencia");
		CHICKENDTO.setType("tipo");
		CHICKEN.setId(ID);
		CHICKEN.setFrecuency("frecuencia");
		CHICKEN.setType("tipo");
		
		Mockito.when(chickenMapper.map(CHICKEN)).thenReturn(CHICKENDTO);
		Mockito.when(chickenService.findById(ID)).thenReturn(CHICKEN);
		
		final ChickenDTO dto = chickenController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		CHICKENDTO.setId(ID);
		
		Chicken c = new Chicken();
		c.setId(23);
		
		ChickenDTO cDTO = new ChickenDTO();
		cDTO.setId(23);
		
		Mockito.when(chickenMapper.map(c)).thenReturn(cDTO);
		Mockito.when(chickenService.findById(ID)).thenReturn(c);
		
		final ChickenDTO dto = chickenController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
}
