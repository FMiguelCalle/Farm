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
import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.chicken.ChickenServiceImpl;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.cow.CowServiceImpl;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.production.ProductionServiceImpl;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidatorImpl;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.notnull.NotNullValidatorImpl;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidatorImpl;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductionService {
	
	private static final Production PRODUCTION = new Production();
	private static final Date PRODUCTIONDATE = new Date();
	private static final Integer PURCHASEPRICE = 20;
	private static final Integer SELLINGPRICE = 30;
	private static final Integer ID = 1;
	private static final Integer ANIMALID = 1;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Integer N = 5;
	private static final Pageable PAGEABLE = new PageRequest(PAGE-1, SIZE);
	private static final Pageable PAGEABLEN = new PageRequest(PAGE-1, N);
	
	
	@InjectMocks
	private ProductionService productionService = new ProductionServiceImpl();
	
	@Mock
	private ProductionDAO productionDAO;
	
	@Mock
	private PageAndSizeValidator pageAndSizeValidator = new PageAndSizeValidatorImpl();
	
	@Mock
	private PathIdAndDTOIdMatchValidator idValidator = new PathIdAndDTOIdMatchValidatorImpl();
	
	@Mock
	private NotNullValidator notNullValidator = new NotNullValidatorImpl();
	
	@Mock
	private UrlElementsExistValidator urlElementsValidator = new UrlElementsExistValidatorImpl();
	
	@Mock
	private ChickenService chickenService = new ChickenServiceImpl();
	
	@Mock
	private CowService cowService = new CowServiceImpl();
	
	/*
	 * Condition 1:
	 * 		- url elements ok.
	 * 		- animal type chicken.
	 */
	@Test
	public void testCreateCondition1() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setAnimalId(ANIMALID);
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken")).thenReturn(true);
		Mockito.when(productionDAO.save(production)).thenReturn(PRODUCTION);
		
		Production p = productionService.create(production, ANIMALID, "chicken", USERID); 
				
		Assert.assertNotNull(p);
		Assert.assertNotNull(p.getId());
		Assert.assertNotNull(p.getProductionDate());
		Assert.assertEquals(p.getProductionDate(), production.getProductionDate());
		Assert.assertNotNull(p.getPurchasePrice());
		Assert.assertEquals(p.getPurchasePrice(), production.getPurchasePrice());
		Assert.assertNotNull(p.getSellingPrice());
		Assert.assertEquals(p.getSellingPrice(), production.getSellingPrice());
		Assert.assertNotNull(p.getAnimalId());
		Assert.assertEquals(p.getAnimalId(), production.getAnimalId());
		
	}
	
	/*
	 * Condition 2:
	 * 		- url elements ok.
	 * 		- animal type cow.
	 */
	@Test
	public void testCreateCondition2() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setAnimalId(ANIMALID);
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "cow")).thenReturn(true);
		Mockito.when(productionDAO.save(production)).thenReturn(PRODUCTION);
		
		Production p = productionService.create(production, ANIMALID, "cow", USERID); 
				
		Assert.assertNotNull(p);
		Assert.assertNotNull(p.getId());
		Assert.assertNotNull(p.getProductionDate());
		Assert.assertEquals(p.getProductionDate(), production.getProductionDate());
		Assert.assertNotNull(p.getPurchasePrice());
		Assert.assertEquals(p.getPurchasePrice(), production.getPurchasePrice());
		Assert.assertNotNull(p.getSellingPrice());
		Assert.assertEquals(p.getSellingPrice(), production.getSellingPrice());
		Assert.assertNotNull(p.getAnimalId());
		Assert.assertEquals(p.getAnimalId(), production.getAnimalId());
		
	}
	
	/*
	 * Condition 3:
	 * 		- url elements not ok.
	 */
	@Test
	public void testCreateCondition3() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setAnimalId(ANIMALID);
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "platypus")).thenThrow(new NullPointerException());
		
		Production p = productionService.create(production, ANIMALID, "chicken", USERID); 
				
		Assert.assertNull(p);
		
	}
	
	/*
	 * Condition 1:
	 * 		- url elements ok.
	 * 		- page and size in range.
	 * 		- Empty List.
	 */
	@Test
	public void testGetAllCondition1() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setAnimalId(ANIMALID);

		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken")).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(productionDAO.findAllByAnimalId(ANIMALID, PAGEABLE)).thenReturn(new PageImpl<Production>(new ArrayList<>()));		
		
		List<Production> productions = productionService.getAll(ANIMALID, "chicken", USERID, PAGE, SIZE);
		
		Assert.assertNotNull(productions);
		
	}
	
	/*
	 * Condition 2:
	 * 		- url elements ok.
	 * 		- page and size in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testGetAllCondition2() {
		PRODUCTION.setProductionDate(PRODUCTIONDATE);
		PRODUCTION.setPurchasePrice(PURCHASEPRICE);
		PRODUCTION.setSellingPrice(SELLINGPRICE);
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Production production = new Production();
		production.setProductionDate(PRODUCTIONDATE);
		production.setPurchasePrice(PURCHASEPRICE);
		production.setSellingPrice(SELLINGPRICE);
		production.setAnimalId(ANIMALID);
				List<Production> productions = new ArrayList<>();
		productions.add(PRODUCTION);

		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken")).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(productionDAO.findAllByAnimalId(ANIMALID, PAGEABLE)).thenReturn(new PageImpl<Production>(productions));		
		
		List<Production> res = productionService.getAll(ANIMALID, "chicken", USERID, PAGE, SIZE);
		
		Assert.assertNotNull(res);
		
	}

	/*
	 * Condition 3:
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testGetAllCondition3() {

		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken")).thenThrow(new NullPointerException());
		
		List<Production> productions = productionService.getAll(ANIMALID, "chicken", USERID, PAGE, SIZE);
		
		Assert.assertNull(productions);
		
	}
	
	/*
	 * Condition 4:
	 * 		- url elements ok.
	 * 		- page and size not in range.
	 */
	@Test(expected=NullPointerException.class)
	public void testGetAllCondition4() {
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken")).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenThrow(new NullPointerException());
		
		List<Production> productions = productionService.getAll(ANIMALID, "chicken", USERID, PAGE, SIZE);
		
		Assert.assertNull(productions);
		
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
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- url elements ok.
	 * 		- animal type chicken.
	 */
	@Test
	public void testUpdateCondition1() throws Exception {
		PRODUCTION.setId(USERID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setId(ID);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenReturn(true);
		
		productionService.update(PRODUCTION, ID, ANIMALID, "chicken", USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- url elements ok.
	 * 		- animal type cow.
	 */
	@Test
	public void testUpdateCondition2() throws Exception {
		PRODUCTION.setId(USERID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setId(ID);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "cow", ID)).thenReturn(true);
		
		productionService.update(PRODUCTION, ID, ANIMALID, "cow", USERID);
		
	}
	
	/*
	 * Condition 3:
	 * 		- Ids matching
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition3() throws Exception {
		PRODUCTION.setId(ID);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenThrow(new NullPointerException());
		
		productionService.update(PRODUCTION, ID, ANIMALID, "chicken", USERID);
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching.
	 * 		- url elements ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition4() throws Exception {
		PRODUCTION.setId(23);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenReturn(true);
		
		productionService.update(PRODUCTION, ID, ANIMALID, "chicken", USERID);
	}
	
	/*
	 * Condition 5:
	 * 		- Ids not matching
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testUpdateCondition5() throws Exception {
		PRODUCTION.setId(23);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenThrow(new NullPointerException());
		
		productionService.update(PRODUCTION, ID, ANIMALID, "chicken", USERID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- Ids matching
	 * 		- url elements ok.
	 */
	@Test
	public void testDeleteCondition1() throws Exception {
		PRODUCTION.setId(USERID);
		PRODUCTION.setAnimalId(ANIMALID);
		PRODUCTION.setId(ID);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenReturn(true);
		
		productionService.delete(PRODUCTION, ID, ANIMALID, "chicken", USERID);
		
	}
	
	/*
	 * Condition 2:
	 * 		- Ids matching
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition2() throws Exception {
		PRODUCTION.setId(ID);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenReturn(true);
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenThrow(new NullPointerException());
		
		productionService.delete(PRODUCTION, ID, ANIMALID, "chicken", USERID);
	}
	
	/*
	 * Condition 3:
	 * 		- Ids not matching.
	 * 		- url elements ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition3() throws Exception {
		PRODUCTION.setId(23);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenReturn(true);
		
		productionService.delete(PRODUCTION, ID, ANIMALID, "chicken", USERID);
	}
	
	/*
	 * Condition 4:
	 * 		- Ids not matching
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testDeleteCondition4() throws Exception {
		PRODUCTION.setId(23);
		
		Mockito.when(idValidator.validateMatchingIds(PRODUCTION.getId(), ID)).thenThrow(new NullPointerException());
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID, ANIMALID, "chicken", ID)).thenThrow(new NullPointerException());
		
		productionService.delete(PRODUCTION, ID, ANIMALID, "chicken", USERID);
		
	}
	
	/*
	 * Condition 1:
	 * 		- animal type chicken.
	 * 		- production not null.
	 */
	@Test
	public void testFindByIdAndAnimalIdAndAnimalTypeAndUserIdCondition1() throws Exception {
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Chicken chicken = new Chicken();
		chicken.setId(ANIMALID);
		chicken.setUserId(USERID);
		
		Mockito.when(chickenService.findByIdAndUserId(ANIMALID, USERID)).thenReturn(chicken);
		Mockito.when(productionDAO.findByIdAndAnimalId(ID, ANIMALID)).thenReturn(PRODUCTION);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenReturn(true);
		
		Production p = productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, "chicken", USERID);
		
		Assert.assertNotNull(p);
		Assert.assertEquals(p.getId(), ID);
		Assert.assertEquals(p.getAnimalId(), ANIMALID);	
	}
	
	/*
	 * Condition 2:
	 * 		- animal type cow.
	 * 		- production not null.
	 */
	@Test
	public void testFindByIdAndAnimalIdAndAnimalTypeAndUserIdCondition2() throws Exception {
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Cow cow = new Cow();
		cow.setId(ANIMALID);
		cow.setUserId(USERID);
		
		Mockito.when(cowService.findByIdAndUserId(ANIMALID, USERID)).thenReturn(cow);
		Mockito.when(productionDAO.findByIdAndAnimalId(ID, ANIMALID)).thenReturn(PRODUCTION);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenReturn(true);
		
		Production p = productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, "cow", USERID);
		
		Assert.assertNotNull(p);
		Assert.assertEquals(p.getId(), ID);
		Assert.assertEquals(p.getAnimalId(), ANIMALID);	
	}
	
	/*
	 * Condition 3:
	 * 		- animal type is not chicken and not cow. 
	 */
	@Test
	public void testFindByIdAndAnimalIdAndAnimalTypeAndUserIdCondition3() {
		Production p = productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, "platypus", USERID);
		
		Assert.assertNull(p);
	}
	
	/*
	 * Condition 4:
	 * 		- animal type chicken.
	 * 		- production is null. 
	 */
	@Test(expected=NullPointerException.class)
	public void testFindByIdAndAnimalIdAndAnimalTypeAndUserIdCondition4() throws Exception{
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Chicken chicken = new Chicken();
		chicken.setId(ANIMALID);
		chicken.setUserId(USERID);
		
		Mockito.when(chickenService.findByIdAndUserId(ANIMALID, USERID)).thenReturn(chicken);
		Mockito.when(productionDAO.findByIdAndAnimalId(ID, ANIMALID)).thenReturn(PRODUCTION);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenThrow(new NullPointerException());		
		
		Production p = productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, "chicken", USERID);
		
		Assert.assertNull(p);
	}
	
	/*
	 * Condition 5:
	 * 		- animal type cow.
	 * 		- production is null. 
	 */
	@Test(expected=NullPointerException.class)
	public void testFindByIdAndAnimalIdAndAnimalTypeAndUserIdCondition5() throws Exception{
		PRODUCTION.setId(ID);
		PRODUCTION.setAnimalId(ANIMALID);
		
		Cow cow = new Cow();
		cow.setId(ANIMALID);
		cow.setUserId(USERID);
		
		Mockito.when(cowService.findByIdAndUserId(ANIMALID, USERID)).thenReturn(cow);
		Mockito.when(productionDAO.findByIdAndAnimalId(ID, ANIMALID)).thenReturn(PRODUCTION);
		Mockito.when(notNullValidator.validateNotNull(PRODUCTION)).thenThrow(new NullPointerException());		
		
		Production p = productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(ID, ANIMALID, "cow", USERID);
		
		Assert.assertNull(p);
	}
	
	
	/*
	 * Condition 1:
	 * 		- url elements ok.
	 * 		- page and n in range.
	 * 		- Empty List.
	 */
	@Test
	public void testfindAllGroupByAnimalIdOrderByEarningCondition1() {
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, N)).thenReturn(true);
		Mockito.when(productionDAO.topNAnimalsFromUser(USERID, PAGEABLEN)).thenReturn(new PageImpl<StatsTopAnimalDTO>(new ArrayList<>()));
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	/*
	 * Condition 2:
	 * 		- url elements ok.
	 * 		- page and n in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testfindAllGroupByAnimalIdOrderByEarningCondition2() {
		List<StatsTopAnimalDTO> preDTOs = new ArrayList<>();
		StatsTopAnimalDTO preDTO = new StatsTopAnimalDTO(ANIMALID, 123L);
		preDTOs.add(preDTO);
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, N)).thenReturn(true);
		Mockito.when(productionDAO.topNAnimalsFromUser(USERID, PAGEABLEN)).thenReturn(new PageImpl<StatsTopAnimalDTO>(preDTOs));
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	/*
	 * Condition 3:
	 * 		- url elements ok.
	 * 		- page and n not in range.
	 * 		- page and size in range.
	 * 		- Empty List.
	 */
	@Test
	public void testfindAllGroupByAnimalIdOrderByEarningCondition3() {
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, N)).thenReturn(false);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(productionDAO.topNAnimalsFromUser(USERID, PAGEABLE)).thenReturn(new PageImpl<StatsTopAnimalDTO>(new ArrayList<>()));
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	/*
	 * Condition 4:
	 * 		- url elements ok.
	 * 		- page and n not in range.
	 * 		- page and size in range.
	 * 		- Not Empty List.
	 */
	@Test
	public void testfindAllGroupByAnimalIdOrderByEarningCondition4() {
		List<StatsTopAnimalDTO> preDTOs = new ArrayList<>();
		StatsTopAnimalDTO preDTO = new StatsTopAnimalDTO(ANIMALID, 123L);
		preDTOs.add(preDTO);
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, N)).thenReturn(false);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(true);
		Mockito.when(productionDAO.topNAnimalsFromUser(USERID, PAGEABLE)).thenReturn(new PageImpl<StatsTopAnimalDTO>(preDTOs));
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	/*
	 * Condition 5:
	 * 		- url elements ok.
	 * 		- page and n not in range.
	 * 		- page and size not in range.
	 */
	@Test
	public void testfindAllGroupByAnimalIdOrderByEarningCondition5() {
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenReturn(true);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, N)).thenReturn(false);
		Mockito.when(pageAndSizeValidator.validatePageAndSize(PAGE, SIZE)).thenReturn(false);
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	/*
	 * Condition 6:
	 * 		- url elements not ok.
	 */
	@Test(expected=NullPointerException.class)
	public void testfindAllGroupByAnimalIdOrderByEarningCondition6() {
		
		Mockito.when(urlElementsValidator.validateUrlElementsExistence(USERID)).thenThrow(new NullPointerException());
		
		List<StatsTopAnimalDTO> dtos = productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE);
		
		Assert.assertNull(dtos);
	}
	
}
