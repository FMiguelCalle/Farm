package com.fmcc.farm.service.statsuserearning;

import java.util.Date;
import java.util.List;

import com.fmcc.farm.dto.StatsUserEarningDTOPost;

public interface StatsUserEarningService {
	
	List<StatsUserEarningDTOPost> usersEarningsBetweenDates(Date firstDate, Date lastDate, Integer page, Integer size);
	
}
