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

import com.fmcc.farm.dao.CowDAO;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestCowService {
	
	private static final Cow COW = new Cow();
	private static final String BREED = "breed";
	private static final Integer ID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE, SIZE);
	
	@InjectMocks
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private CowDAO cowDAO;
	
	@Test
	public void testCreateOK() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		
		Mockito.when(cowDAO.save(cow)).thenReturn(COW);
		
		Cow c = cowService.create(cow);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertEquals(c.getBreed(), cow.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), cow.getProductions());
		
	}
	
	@Test
	public void testCreateKO() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed("KO");
		
		Mockito.when(cowDAO.save(cow)).thenReturn(COW);
		
		Cow u = cowService.create(cow);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getBreed());
		Assert.assertNotNull(u.getProductions());
		Assert.assertTrue(!u.getProductions().equals(cow.getProductions()) ||
							!u.getBreed().equals(cow.getBreed()));		
	}
	
	@Test
	public void testGetAllOK() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		
		Mockito.when(cowDAO.findAllByUserId(USERID, PAGEABLE)).thenReturn(new PageImpl<Cow>(new ArrayList<Cow>()));		
		List<Cow> cows = cowService.getAll(USERID, PAGE, SIZE);
		
		Assert.assertNotNull(cows);
		Assert.assertTrue(cows.size() <= SIZE);
		
	}
	
	@Test
	public void testGetAllKO() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed("KO");
		
		List<Cow> aux = new ArrayList<>();
		aux.add(COW);
		aux.add(cow);
		
		Integer page = 0;
		Integer size = 1;
		
		Mockito.when(cowDAO.findAllByUserId(USERID, new PageRequest(page,size))).thenReturn(new PageImpl<Cow>(aux));		
		List<Cow> cows = cowService.getAll(USERID, page, size);
		
		Assert.assertNotNull(cows);
		Assert.assertFalse(cows.size() <= size );		
	}
	
	@Test
	public void testFindByIdOK() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed(BREED);
		cow.setId(ID);
		
		Mockito.when(cowDAO.findOne(ID)).thenReturn(COW);
		
		Cow c = cowService.findById(ID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), cow.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertEquals(c.getBreed(), cow.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertEquals(c.getProductions(), cow.getProductions());
		
	}
	
	@Test
	public void testFindByIdKO() {
		COW.setBreed(BREED);
		COW.setProductions(new ArrayList<>());
		COW.setId(ID);
		
		Integer id = 23;
		
		Cow cow = new Cow();
		cow.setProductions(new ArrayList<>());
		cow.setBreed("KO");
		cow.setId(id);
		
		Mockito.when(cowDAO.findOne(id)).thenReturn(COW);
		
		Cow c = cowService.findById(id);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getBreed());
		Assert.assertNotNull(c.getProductions());
		Assert.assertTrue(!c.getProductions().equals(cow.getProductions()) ||
							!c.getBreed().equals(cow.getBreed()) ||
							!c.getId().equals(cow.getId()));
		
	}
}
