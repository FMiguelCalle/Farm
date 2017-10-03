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

import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.mappers.chicken.ChickenMapper;
import com.fmcc.farm.mappers.chicken.ChickenMapperImpl;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.mappers.production.ProductionMapperImpl;
import com.fmcc.farm.model.Chicken;

@RunWith(MockitoJUnitRunner.class)
public class TestChickenMapper {
	
	private static final Chicken CHICKEN = new Chicken();
	private static final ChickenDTO CHICKENDTO = new ChickenDTO();
	private static final String FRECUENCY = "frecuency";
	private static final String TYPE = "type";
	private static final Integer ID = 1;
	
	@InjectMocks
	private ChickenMapper ChickenMapper = new ChickenMapperImpl();
	
	@Mock
	private ProductionMapper productionMapper = new ProductionMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Test
	public void testChickenToDTOMapOK() {
		CHICKEN.setId(ID);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKENDTO.setId(ID);
		CHICKENDTO.setFrecuency(FRECUENCY);
		CHICKENDTO.setType(TYPE);
		
		
		Mockito.when(mapper.map(CHICKEN, ChickenDTO.class)).thenReturn(CHICKENDTO);
		Mockito.when(productionMapper.createListIdFromProduction(CHICKEN.getProductions())).thenReturn(new ArrayList<>());
		
		final ChickenDTO dto = ChickenMapper.map(CHICKEN);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), CHICKEN.getId());
		Assert.assertNotNull(dto.getFrecuency());
		Assert.assertEquals(dto.getFrecuency(), CHICKEN.getFrecuency());
		Assert.assertNotNull(dto.getType());
		Assert.assertEquals(dto.getType(), CHICKEN.getType());
	}
	
	@Test
	public void testChickenToDTOMapKO() {
		CHICKEN.setId(ID);
		
		ChickenDTO uDTO = new ChickenDTO();
		uDTO.setId(23);
		
		Mockito.when(mapper.map(CHICKEN, ChickenDTO.class)).thenReturn(uDTO);
		Mockito.when(productionMapper.createListIdFromProduction(CHICKEN.getProductions())).thenReturn(new ArrayList<>());
		
		final ChickenDTO dto = ChickenMapper.map(CHICKEN);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), CHICKEN.getId());
	}
	
	@Test
	public void testDTOToChickenMapOK() {
		CHICKEN.setId(ID);
		CHICKEN.setProductions(new ArrayList<>());
		CHICKEN.setFrecuency(FRECUENCY);
		CHICKEN.setType(TYPE);
		CHICKENDTO.setId(ID);
		CHICKENDTO.setFrecuency(FRECUENCY);
		CHICKENDTO.setType(TYPE);
		
		Mockito.when(mapper.map(CHICKENDTO, Chicken.class)).thenReturn(CHICKEN);
		Mockito.when(productionMapper.createListProductionFromId(CHICKENDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Chicken chicken = ChickenMapper.map(CHICKENDTO);
		
		Assert.assertNotNull(chicken);
		Assert.assertNotNull(chicken.getId());
		Assert.assertEquals(chicken.getId(), CHICKENDTO.getId());
		Assert.assertNotNull(chicken.getFrecuency());
		Assert.assertEquals(chicken.getFrecuency(), CHICKENDTO.getFrecuency());
		Assert.assertNotNull(chicken.getType());
		Assert.assertEquals(chicken.getType(), CHICKENDTO.getType());
	}
	
	@Test
	public void testDTOToChickenMapKO() {
		CHICKENDTO.setId(ID);
		
		Chicken c = new Chicken();
		c.setId(23);
		
		Mockito.when(mapper.map(CHICKENDTO, Chicken.class)).thenReturn(c);
		Mockito.when(productionMapper.createListProductionFromId(CHICKENDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Chicken chicken = ChickenMapper.map(CHICKENDTO);
		
		Assert.assertNotNull(chicken);
		Assert.assertNotNull(chicken.getId());
		Assert.assertNotEquals(chicken.getId(), CHICKENDTO.getId());
	}
	
}
