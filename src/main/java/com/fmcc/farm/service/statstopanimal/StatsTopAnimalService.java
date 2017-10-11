package com.fmcc.farm.service.statstopanimal;

import java.util.List;

import com.fmcc.farm.dto.StatsTopAnimalDTO;

public interface StatsTopAnimalService {
	
	List<StatsTopAnimalDTO> topNAnimalsProfit(Integer userId, Integer n, Integer page, Integer size);
	
}
