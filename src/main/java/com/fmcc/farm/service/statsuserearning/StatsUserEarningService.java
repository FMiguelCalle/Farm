package com.fmcc.farm.service.statsuserearning;

import java.util.Date;
import java.util.List;

import com.fmcc.farm.dto.StatsUserEarningDTO;

public interface StatsUserEarningService {
	
	List<StatsUserEarningDTO> usersEarningsBetweenDates(Date fromDate, Date toDate, Integer page, Integer size);
	
}
