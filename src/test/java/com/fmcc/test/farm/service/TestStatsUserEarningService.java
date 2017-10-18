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

import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.service.statsuserearning.StatsUserEarningService;
import com.fmcc.farm.service.statsuserearning.StatsUserEarningServiceImpl;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.service.user.UserServiceImpl;

@RunWith(value=MockitoJUnitRunner.class)
public class TestStatsUserEarningService {

	private static final Integer ID = 1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 5;
	private static final Date FROMDATE = new Date(1506336868069L);
	private static final Date TODATE = new Date(1506406868069L);
	
	@InjectMocks
	private StatsUserEarningService statsUserEarningService = new StatsUserEarningServiceImpl();
	
	@Mock
	private UserService userService = new UserServiceImpl();
	
	@Test
	public void testUsersEarningsBetweenDatesOKEmptyList() {
		Mockito.when(userService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenReturn(new ArrayList<>());
		
		List<StatsUserEarningDTO> dtos = statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
	}
	
	@Test
	public void testUsersEarningsBetweenDatesOKNotEmptyList() {
		List<StatsUserEarningDTO> preDTOs = new ArrayList<>();
		StatsUserEarningDTO preDTO = new StatsUserEarningDTO(ID, 123L);
		preDTOs.add(preDTO);
		
		Mockito.when(userService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenReturn(preDTOs);
		
		List<StatsUserEarningDTO> dtos = statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);
		
		Assert.assertNotNull(dtos);
		Assert.assertNotNull(dtos.get(0));
	}
	
	@Test
	public void testUsersEarningsBetweenDatesKO() {
		Mockito.when(userService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE)).thenReturn(null);
		
		List<StatsUserEarningDTO> dtos = statsUserEarningService.usersEarningsBetweenDates(FROMDATE, TODATE, PAGE, SIZE);
		
		Assert.assertNull(dtos);
	}
}
