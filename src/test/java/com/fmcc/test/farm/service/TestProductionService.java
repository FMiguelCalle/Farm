package com.fmcc.test.farm.service;

import java.util.ArrayList;
import java.util.Date;
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

import com.fmcc.farm.dao.ProductionDAO;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.ProductionService;
import com.fmcc.farm.service.ProductionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionService {
	
	private static final Production PRODUCTION = new Production();
	private static final Date PRODUCTIONDATE = new Date();
	private static final Integer PURCHASEPRICE = 20;
	private static final Integer SELLINGPRICE = 30;
	private static final Integer ID = 1;
	private static final Integer ANIMALID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE, SIZE);
	
	@InjectMocks
	private ProductionService productionService = new ProductionServiceImpl();
	
	@Mock
	private ProductionDAO productionDAO;
	
	@Test
	public void testCreateOK() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		
		Mockito.when(productionDAO.save(production)).thenReturn(PRODUCTION);
		
		Production c = productionService.create(production);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getProductionDate());
		Assert.assertEquals(c.getProductionDate(), production.getProductionDate());
		Assert.assertNotNull(c.getPurchasePrice());
		Assert.assertEquals(c.getPurchasePrice(), production.getPurchasePrice());
		Assert.assertNotNull(c.getSellingPrice());
		Assert.assertEquals(c.getSellingPrice(), production.getSellingPrice());
		
	}
	
	@Test
	public void testCreateKO() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Production production = new Production();
		production.setProductionDate(new Date(1506336868069L));
		production.setPurchasePrice(10);
		production.setSellingPrice(30);
		
		Mockito.when(productionDAO.save(production)).thenReturn(PRODUCTION);
		
		Production u = productionService.create(production);
		
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getProductionDate());
		Assert.assertNotNull(u.getPurchasePrice());
		Assert.assertNotNull(u.getSellingPrice());
		Assert.assertTrue(!u.getProductionDate().equals(production.getProductionDate()) ||
							!u.getPurchasePrice().equals(production.getPurchasePrice()) ||
							!u.getSellingPrice().equals(production.getSellingPrice()));
		
	}
	
	@Test
	public void testGetAllOK() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		
		Mockito.when(productionDAO.findAllByAnimalId(ANIMALID, PAGEABLE)).thenReturn(new PageImpl<Production>(new ArrayList<Production>()));		
		List<Production> productions = productionService.getAll(ANIMALID, PAGE, SIZE);
		
		Assert.assertNotNull(productions);
		Assert.assertTrue(productions.size() <= SIZE);
		
	}
	
	@Test
	public void testGetAllKO() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Production production = new Production();
		production.setProductionDate(new Date(1506336868069L));
		production.setPurchasePrice(10);
		production.setSellingPrice(30);
		
		List<Production> aux = new ArrayList<>();
		aux.add(PRODUCTION);
		aux.add(production);
		
		Integer page = 0;
		Integer size = 1;
		
		Mockito.when(productionDAO.findAllByAnimalId(ANIMALID, new PageRequest(page,size))).thenReturn(new PageImpl<Production>(aux));		
		List<Production> productions = productionService.getAll(ANIMALID, page, size);
		
		Assert.assertNotNull(productions);
		Assert.assertFalse(productions.size() <= size );		
	}
	
	@Test
	public void testFindByIdOK() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setId(ID);
		
		Mockito.when(productionDAO.findOne(ID)).thenReturn(PRODUCTION);
		
		Production c = productionService.findById(ID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), production.getId());
		Assert.assertNotNull(c.getProductionDate());
		Assert.assertEquals(c.getProductionDate(), production.getProductionDate());
		Assert.assertNotNull(c.getPurchasePrice());
		Assert.assertEquals(c.getPurchasePrice(), production.getPurchasePrice());
		Assert.assertNotNull(c.getSellingPrice());
		Assert.assertEquals(c.getSellingPrice(), production.getSellingPrice());
	}
	
	@Test
	public void testFindByIdKO() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		
		Integer id = 23;
		
		Production production = new Production();
		production.setProductionDate(new Date(1506336868069L));
		production.setPurchasePrice(10);
		production.setSellingPrice(30);
		production.setId(id);
		
		Mockito.when(productionDAO.findOne(id)).thenReturn(PRODUCTION);
		
		Production c = productionService.findById(id);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(c.getProductionDate());
		Assert.assertNotNull(c.getPurchasePrice());
		Assert.assertNotNull(c.getSellingPrice());
		Assert.assertTrue(!c.getProductionDate().equals(production.getProductionDate()) ||
							!c.getPurchasePrice().equals(production.getPurchasePrice()) ||
							!c.getSellingPrice().equals(production.getSellingPrice()) ||
							!c.getId().equals(production.getId()));
		
	}
}
