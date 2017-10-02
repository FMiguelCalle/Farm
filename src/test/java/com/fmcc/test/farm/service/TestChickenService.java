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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fmcc.farm.dao.ChickenDAO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.service.ChickenService;
import com.fmcc.farm.service.ChickenServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestChickenService {
	
	private static final Chicken CHICKEN = new Chicken();
	private static final String FRECUENCY = "frecuency";
	private static final String TYPE = "tipo";
	private static final Integer ID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE, SIZE);
	
	@InjectMocks
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private ChickenDAO chickenDAO;
	
	@Test
	public void testCreateOK() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		
		Mockito.when(chickenDAO.save(chicken)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.create(chicken);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertEquals(c.getFrecuency(), chicken.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertEquals(c.getType(), chicken.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), chicken.getProductions());
		
	}
	
	@Test
	public void testCreateKO() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency("KO");
		chicken.setType("KO");
		
		Mockito.when(chickenDAO.save(chicken)).thenReturn(CHICKEN);
		
		Chicken u = chickenService.create(chicken);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getFrecuency());
		Assert.assertNotNull(u.getType());
		Assert.assertNotNull(u.getProductions());
		Assert.assertTrue(!u.getProductions().equals(chicken.getProductions()) ||
							!u.getFrecuency().equals(chicken.getFrecuency()) ||
							!u.getType().equals(chicken.getType()));
		
	}
	
	@Test
	public void testGetAllOK() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		
		Mockito.when(chickenDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Chicken>(new ArrayList<Chicken>()));		
		List<Chicken> chickens = chickenService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(chickens);
		Assert.assertTrue(chickens.size() <= SIZE);
		
	}
	
	@Test
	public void testGetAllKO() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency("KO");
		chicken.setType("KO");
		
		List<Chicken> aux = new ArrayList<>();
		aux.add(CHICKEN);
		aux.add(chicken);
		
		Integer page = 0;
		Integer size = 1;
		
		Mockito.when(chickenDAO.findAllByUserId(USERID, new PageRequest(page,size))).thenReturn(new PageImpl<Chicken>(aux));		
		List<Chicken> chickens = chickenService.getAll(USERID, page, size);
		
		Assert.assertNotNull(chickens);
		Assert.assertFalse(chickens.size() <= size );		
	}
	
	@Test
	public void testFindByIdOK() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency(FRECUENCY);
		chicken.setType(TYPE);
		chicken.setId(ID);
		
		Mockito.when(chickenDAO.findOne(ID)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.findById(ID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), chicken.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertEquals(c.getFrecuency(), chicken.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertEquals(c.getType(), chicken.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), chicken.getProductions());
		
	}
	
	@Test
	public void testFindByIdKO() {
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setId(ID);
		
		Integer id = 23;
		
		Chicken chicken = new Chicken();
		chicken.setProductions(new ArrayList<>());
		chicken.setFrecuency("KO");
		chicken.setType("KO");
		chicken.setId(id);
		
		Mockito.when(chickenDAO.findOne(id)).thenReturn(CHICKEN);
		
		Chicken c = chickenService.findById(id);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getFrecuency());
		Assert.assertNotNull(c.getType());
		Assert.assertNotNull(c.getProductions());
		Assert.assertTrue(!c.getProductions().equals(chicken.getProductions()) ||
							!c.getFrecuency().equals(chicken.getFrecuency()) ||
							!c.getType().equals(chicken.getType()) ||
							!c.getId().equals(chicken.getId()));
		
	}
}
