package com.fmcc.farm.service;

import java.util.List;

import com.fmcc.farm.dto.StatsTopAnimalDTO;

public interface StatsTopAnimalService {
	
	List<StatsTopAnimalDTO> topNAnimalsProfit(Integer n, Integer userId, Integer page, Integer size);
	
}
