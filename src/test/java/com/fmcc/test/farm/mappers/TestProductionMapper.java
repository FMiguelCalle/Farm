package com.fmcc.test.farm.mappers;

import java.util.Date;

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
	private ProductionMapper ProductionMapper = new ProductionMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
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
		
		final ProductionDTO dto = ProductionMapper.map(PRODUCTION);
		
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
		
		final ProductionDTO dto = ProductionMapper.map(PRODUCTION);
		
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
		
		final Production production = ProductionMapper.map(PRODUCTIONDTO);
		
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
		
		final Production production = ProductionMapper.map(PRODUCTIONDTO);
		
		Assert.assertNotNull(production);
		Assert.assertNotNull(production.getId());
		Assert.assertNotEquals(production.getId(), PRODUCTIONDTO.getId());
	}
	
}
