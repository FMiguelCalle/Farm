package com.fmcc.farm.service;

import java.util.List;

import com.fmcc.farm.model.Animal;

public interface AnimalService {

	List<Animal> findAllByUserId(Integer userId, Integer page, Integer size);
	
	Animal findById(Integer id);
	
	void update(Animal a);
}
