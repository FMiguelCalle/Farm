package com.fmcc.farm.controller;

import java.util.List;

import com.fmcc.farm.dto.AnimalDTO;

public interface AnimalController {
	
	List<AnimalDTO> getAll(Integer page, Integer size, Integer userId);

	AnimalDTO findById(Integer id);
	
}
