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

import com.fmcc.farm.controller.chicken.ChickenController;
import com.fmcc.farm.controller.chicken.ChickenControllerImpl;
import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.mappers.chicken.ChickenMapper;
import com.fmcc.farm.mappers.chicken.ChickenMapperImpl;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;

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
		Mockito.when(chickenService.create(CHICKEN, USERID)).thenReturn(CHICKEN);
		
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
		Mockito.when(chickenService.create(CHICKEN,USERID)).thenReturn(CHICKEN);
		
		final ChickenDTO dto = chickenController.create(cDTO, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
	}
	
	@Test
	public void testGetAllOKEmptyList() throws Exception {
		CHICKEN.setId(ID);
		CHICKENDTO.setId(ID);
		
		final List<Chicken> chickens = new ArrayList<>();
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
	}
	
	@Test
	public void testGetAllOKNotEmptyList() throws Exception {
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		CHICKEN.setFrecuency("FRECUENCY");
		CHICKEN.setType("TYPE");
		CHICKEN.setProductions(new ArrayList<>());
		CHICKENDTO.setId(ID);
		CHICKENDTO.setFrecuency("FRECUENCY");
		CHICKENDTO.setType("TYPE");
		CHICKENDTO.setProductions(new ArrayList<>());
		
		final List<Chicken> chickens = new ArrayList<>();		
		chickens.add(CHICKEN);
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		Mockito.when(chickenMapper.map(CHICKEN)).thenReturn(CHICKENDTO);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNotNull(chickensDTO);
		Assert.assertEquals(chickensDTO.size(), chickens.size());
		Assert.assertEquals(chickensDTO.get(0).getId(), CHICKEN.getId());
		Assert.assertEquals(chickensDTO.get(0).getFrecuency(), CHICKEN.getFrecuency());
		Assert.assertEquals(chickensDTO.get(0).getType(), CHICKEN.getType());
		Assert.assertEquals(chickensDTO.get(0).getProductions().size(), CHICKEN.getProductions().size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetAllKONull() throws Exception {
		
		final List<Chicken> chickens = null;
		
		Mockito.when(chickenService.getAll(USERID, PAGE, SIZE)).thenReturn(chickens);
		
		final List<ChickenDTO> chickensDTO = chickenController.getAll(PAGE, SIZE, USERID);
		
		Assert.assertNull(chickensDTO);
	}
	
	@Test
	public void testUpdateOK() throws Exception {
		
		ChickenDTO dto = new ChickenDTO();
		dto.setId(ID);
		CHICKEN.setId(ID);
		CHICKEN.setUserId(USERID);
		
		Mockito.when(chickenMapper.map(dto)).thenReturn(CHICKEN);
		
		chickenController.update(dto, USERID, ID);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateKO() throws Exception {
		
		ChickenDTO dto = new ChickenDTO();
		dto.setId(ID);
		CHICKEN.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(chickenMapper.map(dto)).thenReturn(CHICKEN);
		Mockito.doThrow(NullPointerException.class).when(chickenService).update(CHICKEN, id, USERID);
			
		chickenController.update(dto, USERID, id);
		
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
		Mockito.when(chickenService.findByIdAndUserId(ID, USERID)).thenReturn(CHICKEN);
		
		final ChickenDTO dto = chickenController.findById(ID,USERID);
		
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
		Mockito.when(chickenService.findByIdAndUserId(ID, USERID)).thenReturn(c);
		
		final ChickenDTO dto = chickenController.findById(ID,USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
}
