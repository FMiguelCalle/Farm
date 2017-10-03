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

import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.mappers.cow.CowMapper;
import com.fmcc.farm.mappers.cow.CowMapperImpl;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.mappers.production.ProductionMapperImpl;
import com.fmcc.farm.model.Cow;

@RunWith(MockitoJUnitRunner.class)
public class TestCowMapper {
	
	private static final Cow COW = new Cow();
	private static final CowDTO COWDTO = new CowDTO();
	private static final String BREED = "breed";
	private static final Integer ID = 1;
	
	@InjectMocks
	private CowMapper CowMapper = new CowMapperImpl();
	
	@Mock
	private ProductionMapper productionMapper = new ProductionMapperImpl();
	
	@Mock
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Test
	public void testCowToDTOMapOK() {
		COW.setId(ID);
		COW.setProductions(new ArrayList<>());
		COW.setBreed(BREED);
		COWDTO.setId(ID);
		COWDTO.setBreed(BREED);
		
		
		Mockito.when(mapper.map(COW, CowDTO.class)).thenReturn(COWDTO);
		Mockito.when(productionMapper.createListIdFromProduction(COW.getProductions())).thenReturn(new ArrayList<>());
		
		final CowDTO dto = CowMapper.map(COW);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(dto.getId(), COW.getId());
		Assert.assertNotNull(dto.getBreed());
		Assert.assertEquals(dto.getBreed(), COW.getBreed());
	}
	
	@Test
	public void testCowToDTOMapKO() {
		COW.setId(ID);
		
		CowDTO uDTO = new CowDTO();
		uDTO.setId(23);
		
		Mockito.when(mapper.map(COW, CowDTO.class)).thenReturn(uDTO);
		Mockito.when(productionMapper.createListIdFromProduction(COW.getProductions())).thenReturn(new ArrayList<>());
		
		final CowDTO dto = CowMapper.map(COW);
		
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getId());
		Assert.assertNotEquals(dto.getId(), COW.getId());
	}
	
	@Test
	public void testDTOToCowMapOK() {
		COW.setId(ID);
		COW.setProductions(new ArrayList<>());
		COW.setBreed(BREED);
		COWDTO.setId(ID);
		COWDTO.setBreed(BREED);
		
		Mockito.when(mapper.map(COWDTO, Cow.class)).thenReturn(COW);
		Mockito.when(productionMapper.createListProductionFromId(COWDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Cow cow = CowMapper.map(COWDTO);
		
		Assert.assertNotNull(cow);
		Assert.assertNotNull(cow.getId());
		Assert.assertEquals(cow.getId(), COWDTO.getId());
		Assert.assertNotNull(cow.getBreed());
		Assert.assertEquals(cow.getBreed(), COWDTO.getBreed());
	}
	
	@Test
	public void testDTOToCowMapKO() {
		COWDTO.setId(ID);
		
		Cow c = new Cow();
		c.setId(23);
		
		Mockito.when(mapper.map(COWDTO, Cow.class)).thenReturn(c);
		Mockito.when(productionMapper.createListProductionFromId(COWDTO.getProductions())).thenReturn(new ArrayList<>());
		
		final Cow cow = CowMapper.map(COWDTO);
		
		Assert.assertNotNull(cow);
		Assert.assertNotNull(cow.getId());
		Assert.assertNotEquals(cow.getId(), COWDTO.getId());
	}
	
}
