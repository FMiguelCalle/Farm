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
import com.fmcc.farm.dto.AnimalDTO;
import com.fmcc.farm.mappers.AnimalMapper;
import com.fmcc.farm.mappers.AnimalMapperImpl;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.service.AnimalService;
import com.fmcc.farm.service.AnimalServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestAnimalController {
	
	private static final Animal ANIMAL = new Chicken();
	private static final AnimalDTO ANIMALDTO = new AnimalDTO();
	private static final Integer ID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private UserAnimalsController animalController = new UserAnimalsControllerImpl();

	@Mock
	private AnimalService animalService = new AnimalServiceImpl();
	
	@Mock
	private AnimalMapper animalMapper = new AnimalMapperImpl();
	
	@Test
	public void testGetAllCondition1() throws Exception {
		ANIMAL.setId(ID);
		ANIMALDTO.setId(ID);
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, PAGE, SIZE)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(PAGE, SIZE, USERID);
		
		
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertTrue(PAGE > 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition2() throws Exception {
		final Integer page = 0;
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, page, SIZE)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(page, SIZE, USERID);
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertEquals(animalsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition3() throws Exception {
		
		final Integer page = 0;
		final Integer size = 0;
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, page, size)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(page, size, USERID);
		
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertEquals(animalsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition4() throws Exception {
		final Integer page = 0;
		final Integer size = 20;
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, page, size)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(page, size, USERID);
		
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertEquals(animalsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size > 10 );
	}
	
	@Test
	public void testGetAllCondition5() throws Exception {
		
		final Integer size = 0;
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, PAGE, size)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertEquals(animalsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition6() throws Exception {
		
		final Integer size = 20;
		
		final List<Animal> animals = new ArrayList<>();
		
		Mockito.when(animalService.findAllByUserId(USERID, PAGE, size)).thenReturn(animals);
		
		final List<AnimalDTO> animalsDTO = animalController.getAll(PAGE, size, USERID);
		
		Assert.assertNotNull(animalsDTO);
		Assert.assertEquals(animalsDTO.size(), animals.size());
		Assert.assertEquals(animalsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size > 10 );
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		
		ANIMAL.setId(ID);
		ANIMALDTO.setId(ID);
		
		Mockito.when(animalMapper.map(ANIMAL)).thenReturn(ANIMALDTO);
		Mockito.when(animalService.findById(ID)).thenReturn(ANIMAL);
		
		final AnimalDTO dto = animalController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		ANIMALDTO.setId(ID);
		
		Animal a = new Cow();
		a.setId(23);
		
		AnimalDTO aDTO = new AnimalDTO();
		aDTO.setId(23);
		
		Mockito.when(animalMapper.map(a)).thenReturn(aDTO);
		Mockito.when(animalService.findById(ID)).thenReturn(a);
		
		final AnimalDTO dto = animalController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
}
