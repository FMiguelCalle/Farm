package com.fmcc.farm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.service.StatsTopAnimalService;

@RestController
@RequestMapping("/user/{user_id}/animal/stats/{n}")
public class StatsTopAnimalControllerImpl implements StatsTopAnimalController{

	@Autowired
	private StatsTopAnimalService statsTopAnimalService;
	
	@Override
	@RequestMapping(method=RequestMethod.GET)
	public List<StatsTopAnimalDTO> topNAnimalsProfit(@PathVariable(name="n") Integer n,
														@PathVariable(name="user_id") Integer userId, 
														@RequestParam(name="page") Integer page,
														@RequestParam(name="size") Integer size) {
		List<StatsTopAnimalDTO> finalDTO = new ArrayList<>();
		statsTopAnimalService.topNAnimalsProfit(n, userId, page, size);
		return finalDTO;
	}

}
