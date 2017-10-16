package com.fmcc.test.farm.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fmcc.farm.controller.statstopanimal.StatsTopAnimalController;
import com.fmcc.farm.controller.statstopanimal.StatsTopAnimalControllerImpl;
import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.service.statstopanimal.StatsTopAnimalService;
import com.fmcc.farm.service.statstopanimal.StatsTopAnimalServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestStatsTopAnimalController {
	
	private static final Integer N = 5;
	private static final Integer USERID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	
	@InjectMocks
	private StatsTopAnimalController statsTopAnimalController = new StatsTopAnimalControllerImpl();

	@Mock
	private StatsTopAnimalService statsTopAnimalService = new StatsTopAnimalServiceImpl();
	
	@Test
	public void testTopNAnimalsProfitOKEmptyList() throws Exception {
		Mockito.when(statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE)).thenReturn(new ArrayList<>());
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalController.topNAnimalsProfit(N, USERID, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	@Test
	public void testTopNAnimalsProfitOKNotEmptyList() throws Exception {
		
		List<StatsTopAnimalDTO> preDtos = new ArrayList<>();
		StatsTopAnimalDTO preDto = new StatsTopAnimalDTO(1, 123L); 
		preDtos.add(preDto);
		
		Mockito.when(statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE)).thenReturn(preDtos);
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalController.topNAnimalsProfit(N, USERID, PAGE, SIZE);

		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void testTopAnimalsProfitKONull() throws Exception {
		
		Mockito.when(statsTopAnimalService.topNAnimalsProfit(USERID, N, PAGE, SIZE)).thenThrow(new NullPointerException());
		
		List<StatsTopAnimalDTO> dtos = statsTopAnimalController.topNAnimalsProfit(N, USERID, PAGE, SIZE);

		Assert.assertNull(dtos);
	}
	
}
