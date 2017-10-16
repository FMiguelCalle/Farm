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

import com.fmcc.farm.controller.statsuserearning.StatsUserEarningController;
import com.fmcc.farm.controller.statsuserearning.StatsUserEarningControllerImpl;
import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.service.statsuserearning.StatsUserEarningService;
import com.fmcc.farm.service.statsuserearning.StatsUserEarningServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestStatsUserEarningController {
	
	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Date FROMDATE = new Date(1506336868069L);
	private static final Date TODATE = new Date(1506406868069L);
	
	@InjectMocks
	private StatsUserEarningController statsUserEarningController = new StatsUserEarningControllerImpl();

	@Mock
	private StatsUserEarningService statsUserEarningService = new StatsUserEarningServiceImpl();
	
	@Test
	public void testUserEarningsBetweenDatesOKEmptyList() throws Exception {
		Mockito.when(statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenReturn(new ArrayList<>());
		
		List<StatsUserEarningDTO> dtos = statsUserEarningController.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	@Test
	public void testUserEarningsBetweenDatesOKNotEmptyList() throws Exception {
		
		List<StatsUserEarningDTO> preDtos = new ArrayList<>();
		StatsUserEarningDTO preDto = new StatsUserEarningDTO(ID, 123L); 
		preDtos.add(preDto);
		
		Mockito.when(statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenReturn(preDtos);
		
		List<StatsUserEarningDTO> dtos = statsUserEarningController.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);

		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void testUserEarningsBetweenDatesKONull() throws Exception {
		
		Mockito.when(statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenThrow(new NullPointerException());
		
		List<StatsUserEarningDTO> dtos = statsUserEarningController.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);

		Assert.assertNull(dtos);
	}
	
}
