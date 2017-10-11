package com.fmcc.farm.controller.statstopanimal;

import java.util.List;

import com.fmcc.farm.dto.StatsTopAnimalDTO;

public interface StatsTopAnimalController {

	List<StatsTopAnimalDTO> topNAnimalsProfit(Integer n, Integer userId, Integer page, Integer size);
	
}
