package com.fmcc.farm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.AnimalDTO;
import com.fmcc.farm.mappers.AnimalMapper;
import com.fmcc.farm.service.AnimalService;

@RestController
@RequestMapping("/user/{user_id}/animal")
public class AnimalControllerImpl implements AnimalController{
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private AnimalMapper animalMapper;
	
	@Override
	@RequestMapping(method=RequestMethod.GET)
	public List<AnimalDTO> getAll(@RequestParam(name="page",defaultValue="1") Integer page,
									@RequestParam(name="size",defaultValue="5") Integer size, 
									@PathVariable(name="user_id") Integer userId) {
		final List<AnimalDTO> dtos = new ArrayList<>();
		if(page > 0 && size < 11 && size>0) {
			animalService.findAllByUserId(userId, page-1, size).forEach(a -> {
				dtos.add(animalMapper.map(a));
			});
		}
		return dtos;
	}

	@Override
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public AnimalDTO findById(@PathVariable Integer id) {
		return animalMapper.map(animalService.findById(id));
	}

}
