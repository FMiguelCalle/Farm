package com.fmcc.test.farm.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fmcc.farm.dao.AnimalDAO;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.service.AnimalService;
import com.fmcc.farm.service.AnimalServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestAnimalService {
	
	private static final Animal ANIMAL = new Chicken();
	private static final Integer USERID = 1;
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE, SIZE);
	
	@InjectMocks
	private AnimalService animalService = new AnimalServiceImpl();
	
	@Mock
	private AnimalDAO animalDAO;
		
	@Test
	public void testFindAllByUserIdOK() {
		ANIMAL.setProductions(new ArrayList<>());
		ANIMAL.setId(ID);
		
		Animal animal = new Chicken();
		animal.setProductions(new ArrayList<>());
		
		Page<Animal> animalsPage = new PageImpl<Animal>(new ArrayList<Animal>());
		
		Mockito.when(animalDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(animalsPage);		
		
		List<Animal> animals = animalService.findAllByUserId(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(animals);
		Assert.assertTrue(animals.size() <= SIZE);
		
	}
	
	@Test
	public void testFindAllByUserIdKO() {
		ANIMAL.setProductions(new ArrayList<>());
		ANIMAL.setId(ID);
		
		Animal animal = new Chicken();
		animal.setProductions(new ArrayList<>());
		
		List<Animal> aux = new ArrayList<>();
		aux.add(ANIMAL);
		aux.add(animal);
		
		Integer page = 0;
		Integer size = 1;
		
		Page<Animal> animalsPage = new PageImpl<Animal>(aux);
		
		Mockito.when(animalDAO.findAllByUserId(USERID,new PageRequest(page,size))).thenReturn(animalsPage);
		List<Animal> animals = animalService.findAllByUserId(USERID, page, size);
		
		Assert.assertNotNull(animals);
		Assert.assertFalse(animals.size() <= size );		
	}
	
	@Test
	public void testFindByIdOK() {
		ANIMAL.setProductions(new ArrayList<>());
		ANIMAL.setId(ID);
		
		Animal animal = new Chicken();
		animal.setProductions(new ArrayList<>());
		animal.setId(ID);
		
		Mockito.when(animalDAO.findOne(ID)).thenReturn(ANIMAL);
		
		Animal u = animalService.findById(ID);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertEquals(u.getId(), animal.getId());
		Assert.assertNotNull(u.getProductions());
		Assert.assertEquals(u.getProductions(), animal.getProductions());
		
	}
	
	@Test
	public void testFindByIdKO() {
		ANIMAL.setProductions(new ArrayList<>());
		ANIMAL.setId(ID);
		
		Integer id = 23;
		
		Animal animal = new Chicken();
		animal.setProductions(new ArrayList<>());
		animal.setId(id);
		
		Mockito.when(animalDAO.findOne(id)).thenReturn(ANIMAL);
		
		Animal u = animalService.findById(id);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getProductions());
		Assert.assertTrue(!u.getProductions().equals(animal.getProductions()) ||
							!u.getId().equals(animal.getId()));
		
	}
}
