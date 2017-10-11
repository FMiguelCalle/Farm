package com.fmcc.farm.controller.statsuserearning;

import java.util.Date;
import java.util.List;

import com.fmcc.farm.dto.StatsUserEarningDTO;

public interface StatsUserEarningController {

	List<StatsUserEarningDTO> usersEarningsBetweenDates(Date fromDate, Date toDate, Integer page, Integer size);
	
}
