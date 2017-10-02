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

import com.fmcc.farm.controller.ProductionController;
import com.fmcc.farm.controller.ProductionControllerImpl;
import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.mappers.ProductionMapper;
import com.fmcc.farm.mappers.ProductionMapperImpl;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.ChickenService;
import com.fmcc.farm.service.ChickenServiceImpl;
import com.fmcc.farm.service.CowService;
import com.fmcc.farm.service.CowServiceImpl;
import com.fmcc.farm.service.ProductionService;
import com.fmcc.farm.service.ProductionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionController {
	
	private static final Production PRODUCTION = new Production();
	private static final ProductionDTO PRODUCTIONDTO = new ProductionDTO();
	private static final Date PRODUCTIONDATE = new Date();
	private static final Integer PURCHASEPRICE = 20;
	private static final Integer SELLINGPRICE = 30;
	private static final Integer ANIMALID = 1;
	private static final String ANIMALTYPECHICKEN = "chicken";
	private static final String ANIMALTYPECOW = "cow";
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
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
	public void testCreateOKChicken() throws Exception {
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
		Mockito.when(productionService.create(PRODUCTION)).thenReturn(PRODUCTION);
		
		
		final ProductionDTO c = productionController.create(dto, ANIMALID, animalType);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), PRODUCTIONDTO.getId());
		Assert.assertEquals(c.getProductionDate(), PRODUCTIONDTO.getProductionDate());
		Assert.assertEquals(c.getPurchasePrice(), PRODUCTIONDTO.getPurchasePrice());
		Assert.assertEquals(c.getSellingPrice(), PRODUCTIONDTO.getSellingPrice());
		Assert.assertEquals(animalType,ANIMALTYPECHICKEN);
	}
	
	@Test
	public void testCreateKOChicken() throws Exception {
		
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
		Mockito.when(productionService.create(PRODUCTION)).thenReturn(PRODUCTION);
		
		final ProductionDTO dto = productionController.create(cDTO, ANIMALID, animalType);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
		Assert.assertEquals(animalType,ANIMALTYPECHICKEN);
	}
	
	@Test
	public void testCreateOKCow() throws Exception {
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		ProductionDTO dto = new ProductionDTO();
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.create(PRODUCTION)).thenReturn(PRODUCTION);
		
		final ProductionDTO c = productionController.create(dto, ANIMALID, ANIMALTYPECOW);
		
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getId());
		Assert.assertEquals(c.getId(), PRODUCTIONDTO.getId());
		Assert.assertEquals(c.getProductionDate(), PRODUCTIONDTO.getProductionDate());
		Assert.assertEquals(c.getPurchasePrice(), PRODUCTIONDTO.getPurchasePrice());
		Assert.assertEquals(c.getSellingPrice(), PRODUCTIONDTO.getSellingPrice());
		Assert.assertTrue(ANIMALTYPECOW=="cow");
	}
	
	@Test
	public void testCreateKOCow() throws Exception {
		
		PRODUCTIONDTO.setId(23);
		PRODUCTION.setId(ID);
		ProductionDTO cDTO = new ProductionDTO();
		
		Mockito.when(productionMapper.map(cDTO)).thenReturn(PRODUCTION);
		Mockito.when(productionMapper.map(PRODUCTION)).thenReturn(PRODUCTIONDTO);
		Mockito.when(productionService.create(PRODUCTION)).thenReturn(PRODUCTION);
		
		final ProductionDTO dto = productionController.create(cDTO, ANIMALID, ANIMALTYPECOW);
		
		Assert.assertNotNull(dto);
		Assert.assertNotEquals(dto.getId(), ID);
		Assert.assertTrue(ANIMALTYPECOW=="cow");
	}
	
	@Test
	public void testGetAllCondition1() throws Exception {
		PRODUCTIONDTO.setId(ID);
		PRODUCTIONDTO.setProductionDate(PRODUCTIONDATE);
		PRODUCTIONDTO.setPurchasePrice(PURCHASEPRICE);
		PRODUCTIONDTO.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, PAGE, SIZE)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(PAGE, SIZE, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertTrue(PAGE > 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition2() throws Exception {
		
		final Integer page = 0;
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, page, SIZE)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(page, SIZE, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertEquals(productionsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && (SIZE > 0 && SIZE <= 10));
	}
	
	@Test
	public void testGetAllCondition3() throws Exception {
		
		final Integer page = 0;
		final Integer size = 0;
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, page, size)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(page, size, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertEquals(productionsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition4() throws Exception {
		final Integer page = 0;
		final Integer size = 20;
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, page, size)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(page, size, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertEquals(productionsDTO.size(), 0);
		Assert.assertTrue(page <= 0 && size > 10 );
	}
	
	@Test
	public void testGetAllCondition5() throws Exception {
		
		final Integer size = 0;
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, PAGE, size)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(PAGE, size, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertEquals(productionsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size <= 0 );
	}
	
	@Test
	public void testGetAllCondition6() throws Exception {
		
		final Integer size = 20;
		
		final List<Production> productions = new ArrayList<>();
		
		Mockito.when(productionService.getAll(ANIMALID, PAGE, size)).thenReturn(productions);
		
		final List<ProductionDTO> productionsDTO = productionController.getAll(PAGE, size, ANIMALID);
		
		Assert.assertNotNull(productionsDTO);
		Assert.assertEquals(productionsDTO.size(), productions.size());
		Assert.assertEquals(productionsDTO.size(), 0);
		Assert.assertTrue(PAGE > 0 && size > 10 );
	}
	
	@Test
	public void testUpdateCondition1Chicken() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		PRODUCTION.setId(ID);
		
		final Integer id = ID;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECHICKEN, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2Chicken() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		PRODUCTION.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECHICKEN, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3Chicken() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(null);
		
		final Integer id = ID;
		productionController.update(dto, ANIMALID, ANIMALTYPECHICKEN, id);
		Assert.assertNull(dto.getId());
		
	}
	
	@Test
	public void testUpdateCondition1Cow() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		PRODUCTION.setId(ID);
		
		final Integer id = ID;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECOW, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition2Cow() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		PRODUCTION.setId(ID);
		
		final Integer id = 23;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.update(dto, ANIMALID, ANIMALTYPECOW, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3Cow() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(null);
		
		final Integer id = ID;
		productionController.update(dto, ANIMALID, ANIMALTYPECOW, id);
		Assert.assertNull(dto.getId());
		
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
		Mockito.when(productionService.findById(ID)).thenReturn(PRODUCTION);
		
		final ProductionDTO dto = productionController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testFindByIdKO() throws Exception {	
		PRODUCTIONDTO.setId(ID);
		
		Production c = new Production();
		c.setId(23);
		
		ProductionDTO cDTO = new ProductionDTO();
		cDTO.setId(23);
		
		Mockito.when(productionMapper.map(c)).thenReturn(cDTO);
		Mockito.when(productionService.findById(ID)).thenReturn(c);
		
		final ProductionDTO dto = productionController.findById(ID);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), ID);
		
	}
	
	@Test
	public void testDeleteCondition1() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		final Integer id = ID;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.delete(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition2() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(ID);
		dto.setProductionDate(PRODUCTIONDATE);
		dto.setPurchasePrice(PURCHASEPRICE);
		dto.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		
		final Integer id = 23;
		
		Mockito.when(productionMapper.map(dto)).thenReturn(PRODUCTION);
		
		productionController.delete(dto, id);
		
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), id);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition3() throws Exception {
		
		ProductionDTO dto = new ProductionDTO();
		dto.setId(null);
		
		final Integer id = ID;
		
		productionController.delete(dto, id);
		
		Assert.assertNull(dto.getId());
		
	}
	
}
