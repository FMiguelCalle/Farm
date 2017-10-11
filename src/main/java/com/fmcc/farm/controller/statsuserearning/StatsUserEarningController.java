package com.fmcc.farm.controller.statsuserearning;

import java.util.List;

import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.dto.StatsUserEarningDTOPost;

public interface StatsUserEarningController {

	List<StatsUserEarningDTOPost> usersEarningsBetweenDates(StatsUserEarningDTO dto, Integer page, Integer size);
	
}
