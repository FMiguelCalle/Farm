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

import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.production.ProductionServiceImpl;
import com.fmcc.farm.service.statstopanimal.StatsTopAnimalService;
import com.fmcc.farm.service.statstopanimal.StatsTopAnimalServiceImpl;

@RunWith(value=MockitoJUnitRunner.class)
public class TestStatsTopAnimalService {

	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Integer N = 5;
	
	@InjectMocks
	private StatsTopAnimalService statsTopAnimalService = new StatsTopAnimalServiceImpl();
	
	@Mock
	private ProductionService productionService = new ProductionServiceImpl();
	
	@Test
	public void testTopNAnimalsProfitOKEmptyList() {
		Mockito.when(productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE)).thenReturn(new ArrayList<>());
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	@Test
	public void testTopNAnimalsProfitOKNotEmptyList() {
		List<StatsTopAnimalDTO> preDTOs = new ArrayList<>();
		StatsTopAnimalDTO preDTO = new StatsTopAnimalDTO(1, 123L);
		preDTOs.add(preDTO);
		
		Mockito.when(productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE)).thenReturn(preDTOs);
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	@Test
	public void testTopNAnimalsProfitKO() {
		Mockito.when(productionService.findAllGroupByAnimalIdOrderByEarning(USERID, N, PAGE, SIZE)).thenReturn(null);
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE);
		
		Assert.assertNull(dtos);
	}
}
