package com.fmcc.farm.service.statstopanimal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fmcc.farm.dto.StatsTopAnimalDTO;

@Service
public class StatsTopAnimalServiceImpl implements StatsTopAnimalService{

	
	@Override
	public List<StatsTopAnimalDTO> topNAnimalsProfit(Integer n, Integer userId, Integer page, Integer size) {
		List<StatsTopAnimalDTO> topAnimals = new ArrayList<>();
		return topAnimals;
	}

}
