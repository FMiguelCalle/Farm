package com.fmcc.farm.controller;

import java.util.List;

import com.fmcc.farm.dto.StatsUserEarningDTO;

public interface StatsUserEarningController {

	List<StatsUserEarningDTO> userEarningBetweenDates(StatsUserEarningDTO dto, Integer userId, Integer page, Integer size);
	
}
