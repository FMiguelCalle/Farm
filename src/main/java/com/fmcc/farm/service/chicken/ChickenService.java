package com.fmcc.farm.service.chicken;

import java.util.List;

import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;

public interface ChickenService {

	Chicken create(Chicken t, Integer userId);
	
	void update(Chicken t, Integer pathId, Integer userId);
	
	List<Chicken> getAll(Integer userId, Integer page, Integer size);

	Chicken findById(Integer id);
	
	void addNewProduction(Production p, Integer animalId, Integer userId);
	
	Chicken findByIdAndUserId(Integer id, Integer userId);

}
