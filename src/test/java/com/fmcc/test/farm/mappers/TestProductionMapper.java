package com.fmcc.test.farm.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.mappers.production.ProductionMapperImpl;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.production.ProductionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionMapper {
	
	private static final Production PRODUCTION = new Production();
	private static final ProductionDTO PRODUCTIONDTO = new ProductionDTO();
	private static final Date PRODUCTIONDATE = new Date();
	private static final Integer PURCHASEPRICE = 20;
	private static final Integer SELLINGPRICE = 30;
	private static final Integer ANIMALID = 1;
	private static final Integer ID = 1;
	
	@InjectMocks
	private ProductionMapper productionMapper = new ProductionMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Mock
	private ProductionService productionService = new ProductionServiceImpl();
	
	@Test
	public void testProductionToDTOMapOK() {
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		
		
		Mockito.when(mapper.map(PRODUCTION, ProductionDTO.class)).thenReturn(PRODUCTIONDTO);
		
		final ProductionDTO dto = productionMapper.map(PRODUCTION);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), PRODUCTION.getId());
		Assert.assertNotNull(dto.getProductionDate());
		Assert.assertEquals(dto.getProductionDate(), PRODUCTION.getProductionDate());
		Assert.assertNotNull(dto.getPurchasePrice());
		Assert.assertEquals(dto.getPurchasePrice(), PRODUCTION.getPurchasePrice());
		Assert.assertNotNull(dto.getSellingPrice());
		Assert.assertEquals(dto.getSellingPrice(), PRODUCTION.getSellingPrice());
	}
	
	@Test
	public void testProductionToDTOMapKO() {
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		ProductionDTO uDTO = new ProductionDTO();
		uDTO.setId(23);
		
		Mockito.when(mapper.map(PRODUCTION, ProductionDTO.class)).thenReturn(uDTO);
		
		final ProductionDTO dto = productionMapper.map(PRODUCTION);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), PRODUCTION.getId());
	}
	
	@Test
	public void testDTOToProductionMapOK() {
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		
		Mockito.when(mapper.map(PRODUCTIONDTO, Production.class)).thenReturn(PRODUCTION);
		
		final Production production = productionMapper.map(PRODUCTIONDTO);
		
		Assert.assertNotNull(production);
		Assert.assertNotNull(production.getId());
		Assert.assertEquals(production.getId(), PRODUCTIONDTO.getId());
		Assert.assertNotNull(production.getProductionDate());
		Assert.assertEquals(production.getProductionDate(), PRODUCTIONDTO.getProductionDate());
		Assert.assertNotNull(production.getPurchasePrice());
		Assert.assertEquals(production.getPurchasePrice(), PRODUCTIONDTO.getPurchasePrice());
		Assert.assertNotNull(production.getSellingPrice());
		Assert.assertEquals(production.getSellingPrice(), PRODUCTIONDTO.getSellingPrice());
	}
	
	@Test
	public void testDTOToProductionMapKO() {
		PRODUCTIONDTO.setId(ID);
		
		Production c = new Production();
		c.setId(23);
		c.setProductionDate(new Date(1506336868069L));
		c.setPurchasePrice(10);
		c.setSellingPrice(30);
		
		Mockito.when(mapper.map(PRODUCTIONDTO, Production.class)).thenReturn(c);
		
		final Production production = productionMapper.map(PRODUCTIONDTO);
		
		Assert.assertNotNull(production);
		Assert.assertNotNull(production.getId());
		Assert.assertNotEquals(production.getId(), PRODUCTIONDTO.getId());
	}
	
	@Test
	public void testCreateListIdFromProductionOKEmptyList() {
		List<Production> productions = new ArrayList<>();
		
		List<Integer> ids = productionMapper.createListIdFromProduction(productions);
		
		Assert.assertNotNull(ids);
		Assert.assertEquals(ids.size(), productions.size());
	}
	
	@Test
	public void testCreateListIdFromProductionOKNotEmptyList() {
		List<Production> productions = new ArrayList<>();
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		productions.add(PRODUCTION);
		
		List<Integer> ids = productionMapper.createListIdFromProduction(productions);
		
		Assert.assertNotNull(ids);
		Assert.assertEquals(ids.size(), productions.size());
		Assert.assertNotNull(ids.get(0));
		Assert.assertEquals(ids.get(0), PRODUCTION.getId());
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateListIdFromProductionKO() {
		List<Production> productions = null;
		
		List<Integer> ids = productionMapper.createListIdFromProduction(productions);
		
		Assert.assertNull(ids);
	}
	
	@Test
	public void testCreateListProductionFromIdOKEmptyList() {
		List<Integer> ids = new ArrayList<>();
		
		List<Production> productions = productionMapper.createListProductionFromId(ids);
		
		Assert.assertNotNull(productions);
		Assert.assertEquals(productions.size(), ids.size());
	}
	
	@Test
	public void testCreateListProductionFromIdOKNotEmptyList() {
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		List<Integer> ids = new ArrayList<>();
		ids.add(ID);
		
		Mockito.when(productionService.findById(ID)).thenReturn(PRODUCTION);
		
		List<Production> productions = productionMapper.createListProductionFromId(ids);
		
		Assert.assertNotNull(productions);
		Assert.assertEquals(productions.size(), ids.size());
		Assert.assertNotNull(productions.get(0));
		Assert.assertEquals(productions.get(0).getId(), ID);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateListProductionFromIdKO() {
		List<Integer> ids = null;
		
		List<Production> productions = productionMapper.createListProductionFromId(ids);
		
		Assert.assertNull(productions);
	}
}
