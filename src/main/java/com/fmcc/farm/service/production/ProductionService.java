package com.fmcc.farm.service.production;

import java.util.List;

import com.fmcc.farm.model.Production;

public interface ProductionService {

	Production create(Production t, Integer animalId, String animalType, Integer userId);
	
	void delete(Production t, Integer pathId);

	void update(Production t, Integer pathId, Integer animalId, String animalType, Integer userId);
	
	List<Production> getAll(Integer animalId, Integer page, Integer size);

	Production findByIdAndAnimalIdAndAnimalTypeAndUserId(Integer id, Integer animalId, String animalType, Integer userId);
	
	Production findById(Integer id);
	
	List<Production> findAllGroupByAnimalIdOrderByEarning(Integer page, Integer size);
	
}
