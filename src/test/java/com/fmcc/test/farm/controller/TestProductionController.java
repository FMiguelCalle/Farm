package com.fmcc.test.farm.controller;

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

import com.fmcc.farm.controller.production.ProductionController;
import com.fmcc.farm.controller.production.ProductionControllerImpl;
import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.mappers.production.ProductionMapperImpl;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.production.ProductionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionController {
	
	private static final Production PRODUCTION = new Production();
	private static final ProductionDTO PRODUCTIONDTO = new ProductionDTO();
	private static final Date PRODUCTIONDATE = new Date();
	private static final Integer PURCHASEPRICE = 20;
	private static final Integer SELLINGPRICE = 30;
	private static final Integer ANIMALID = 1;
	private static final String ANIMALTYPECHICKEN = "chicken";
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Integer USERID = 1;
	
	@InjectMocks
	private ProductionController productionController = new ProductionControllerImpl();

	@Mock
	private ProductionService productionService = new ProductionServiceImpl();

	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private CowService cowService = new CowServiceImpl();
	
	@Mock
	private ProductionMapper productionMapper = new ProductionMapperImpl();

	
	@Test
	public void testCreateOK() throws Exception {
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		String animalType = "chicken";
		
		ProductionDTO dto = new ProductionDTO();
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);

		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.create(PRODUCTION, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		
		final ProductionDTO c = productionController.create(dto, ANIMALID, animalType, USERID);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), PRODUCTIONDTO.getId());
		Assert.assertEquals(c.getProductionDate(), PRODUCTIONDTO.getProductionDate());
		Assert.assertEquals(c.getPurchasePrice(), PRODUCTIONDTO.getPurchasePrice());
		Assert.assertEquals(c.getSellingPrice(), PRODUCTIONDTO.getSellingPrice());
		Assert.assertEquals(animalType,ANIMALTYPECHICKEN);
	}
	
	@Test
	public void testCreateKO() throws Exception {
		
		PRODUCTIONDTO.setId(23);
		PRODUCTIONDTO.setProductionDate(new Date(1506336868069L));
		PRODUCTIONDTO.setPurchasePrice(10);
		PRODUCTIONDTO.setSellingPrice(30);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		ProductionDTO cDTO = new ProductionDTO();
		cDTO.setProductionDate(new Date(1506336868069L));
		cDTO.setPurchasePrice(10);
		cDTO.setSellingPrice(30);
	
		String animalType = "chicken";
		
		Mockito.when(productionMapper.map(cDTO)).thenReturn(PRODUCTION);
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.create(PRODUCTION, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		final ProductionDTO dto = productionController.create(cDTO, ANIMALID, animalType,USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
		Assert.assertEquals(animalType,ANIMALTYPECHICKEN);
	}
	
	@Test
	public void testGetAllOKEmptyList() throws Exception {
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, ANIMALTYPECHICKEN, USERID, PAGE, SIZE)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(ANIMALID, ANIMALTYPECHICKEN, USERID, PAGE, SIZE);
		
		Assert.assertNotNull(productionsDTO);
	}
	
	@Test
	public void testGetAllOKNotEmptyList() throws Exception {
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		
		final List<Production> productions = new ArrayList<>();
		productions.add(PRODUCTION);
		
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.getAll(ANIMALID,ANIMALTYPECHICKEN,USERID, PAGE, SIZE)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(ANIMALID, ANIMALTYPECHICKEN, USERID, PAGE, SIZE);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertNotNull(productionsDTO.get(0));
		Assert.assertNotNull(productionsDTO.get(0).getId());
		Assert.assertNotNull(productionsDTO.get(0).getProductionDate());
		Assert.assertNotNull(productionsDTO.get(0).getPurchasePrice());
		Assert.assertNotNull(productionsDTO.get(0).getSellingPrice());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetAllKONull() throws Exception {
		
		final List<Production> productions = null;
		
		Mockito.when(productionService.getAll(ANIMALID, ANIMALTYPECHICKEN, USERID, PAGE, SIZE)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(ANIMALID, ANIMALTYPECHICKEN, USERID, PAGE, SIZE);
		
		Assert.assertNull(productionsDTO);
	}
	
	
	@Test
	public void testUpdateConditionOK() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setAnimalId(ANIMALID);
		
		final Integer id = ID;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECHICKEN, id, USERID);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateKO() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setAnimalId(ANIMALID);
		
		final Integer id = 23;
		
		Mockito.doThrow(NullPointerException.class).when(productionService).update(PRODUCTION, id, ANIMALID, ANIMALTYPECHICKEN, USERID);
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECHICKEN, id, USERID);
		
	}
	
	@Test
	public void testFindByIdOK() throws Exception {
		PRODUCTIONDTO.setId(ID);
		PRODUCTION.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(PRODUCTION);
		
		final ProductionDTO dto = productionController.findById(ID, ANIMALID, ANIMALTYPECHICKEN, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		Assert.assertNotNull(dto.getProductionDate());
		Assert.assertNotNull(dto.getPurchasePrice());
		Assert.assertNotNull(dto.getSellingPrice());
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		PRODUCTIONDTO.setId(ID);
		
		Production c = new Production();
		c.setId(23);
		
		ProductionDTO cDTO = new ProductionDTO();
		cDTO.setId(23);
		
		Mockito.when(productionMapper.map(c)).thenReturn(cDTO);
		Mockito.when(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, ANIMALTYPECHICKEN, USERID)).thenReturn(c);
		
		final ProductionDTO dto = productionController.findById(ID, ANIMALID, ANIMALTYPECHICKEN, USERID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testDeleteOK() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		final Integer id = ID;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.delete(dto, id, ANIMALID, ANIMALTYPECHICKEN, USERID);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition2() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		final Integer id = 23;

		Mockito.doThrow(NullPointerException.class).when(productionService).delete(PRODUCTION, id, ANIMALID, ANIMALTYPECHICKEN, USERID);
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.delete(dto, id, ANIMALID, ANIMALTYPECHICKEN, USERID);
		
	}
	
}
