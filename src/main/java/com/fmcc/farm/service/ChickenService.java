package com.fmcc.farm.service;

import java.util.List;

import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;

public interface ChickenService {

	Chicken create(Chicken t);

	void update(Chicken t);

	List<Chicken> getAll(Integer userId, Integer page, Integer size);

	Chicken findById(Integer id);
	
	void addNewProduction(Production p, Integer animalId);
}
