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

import com.fmcc.farm.dto.AnimalDTO;
import com.fmcc.farm.mappers.AnimalMapper;
import com.fmcc.farm.mappers.AnimalMapperImpl;
import com.fmcc.farm.mappers.ProductionMapper;
import com.fmcc.farm.mappers.ProductionMapperImpl;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Chicken;

@RunWith(MockitoJUnitRunner.class)
public class TestAnimalMapper {
	
	private static final Animal ANIMAL = new Chicken();
	private static final AnimalDTO ANIMALDTO = new AnimalDTO();
	private static final Integer ID = 1;
	
	@InjectMocks
	private AnimalMapper animalMapper = new AnimalMapperImpl();
	
	@Mock
	private ProductionMapper productionMapper = new ProductionMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Test
	public void testAnimalToDTOMapOK() {
		ANIMAL.setId(ID);
		ANIMAL.setProductions(new ArrayList<>());
		ANIMALDTO.setId(ID);
		
		
		Mockito.when(mapper.map(ANIMAL, AnimalDTO.class)).thenReturn(ANIMALDTO);
		Mockito.when(productionMapper.createListIdFromProduction(ANIMAL.getProductions())).thenReturn(new ArrayList<>());
		
		final AnimalDTO dto = animalMapper.map(ANIMAL);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ANIMAL.getId());
	}
	
	@Test
	public void testAnimalToDTOMapKO() {
		ANIMAL.setId(ID);
		
		AnimalDTO uDTO = new AnimalDTO();
		uDTO.setId(23);
		
		Mockito.when(mapper.map(ANIMAL, AnimalDTO.class)).thenReturn(uDTO);
		Mockito.when(productionMapper.createListIdFromProduction(ANIMAL.getProductions())).thenReturn(new ArrayList<>());
		
		final AnimalDTO dto = animalMapper.map(ANIMAL);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ANIMAL.getId());
	}
	
	@Test
	public void testDTOToAnimalMapOK() {
		ANIMAL.setId(ID);
		ANIMALDTO.setId(ID);
		
		Mockito.when(mapper.map(ANIMALDTO, Animal.class)).thenReturn(ANIMAL);
		Mockito.when(productionMapper.createListProductionFromId(ANIMALDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Animal animal = animalMapper.map(ANIMALDTO);
		
		Assert.assertNotNull(animal);
		Assert.assertNotNull(animal.getId());
		Assert.assertEquals(animal.getId(), ANIMALDTO.getId());
	}
	
	@Test
	public void testDTOToAnimalMapKO() {
		ANIMALDTO.setId(ID);
		
		Animal u = new Chicken();
		u.setId(23);
		
		Mockito.when(mapper.map(ANIMALDTO, Animal.class)).thenReturn(u);
		Mockito.when(productionMapper.createListProductionFromId(ANIMALDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Animal animal = animalMapper.map(ANIMALDTO);
		
		Assert.assertNotNull(animal);
		Assert.assertNotNull(animal.getId());
		Assert.assertNotEquals(animal.getId(), ANIMALDTO.getId());
	}
	
}
