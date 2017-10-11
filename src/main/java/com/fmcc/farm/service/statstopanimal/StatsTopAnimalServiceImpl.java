package com.fmcc.farm.service.statstopanimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.service.production.ProductionService;

@Service
public class StatsTopAnimalServiceImpl implements StatsTopAnimalService{

	@Autowired
	private ProductionService productionService;
	
	@Override
	public List<StatsTopAnimalDTO> topNAnimalsProfit(Integer userId, Integer n, Integer page, Integer size) {
		return productionService.findAllGroupByAnimalIdOrderByEarning(userId, n, page, size);
	}

}
