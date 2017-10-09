package com.fmcc.farm.service.production;

import java.util.List;

import com.fmcc.farm.model.Production;

public interface ProductionService {

	Production create(Production t, Integer animalId, String animalType, Integer userId) throws NullPointerException;
	
	void delete(Production t, Integer pathId, Integer animalId, String animalType, Integer userId) throws NullPointerException;
	
	void update(Production t, Integer pathId, Integer animalId, String animalType, Integer userId) throws NullPointerException;
	
	List<Production> getAll(Integer animalId, String animalType, Integer userId, Integer page, Integer size);
	
	Production findByIdAndAnimalIdAndAnimalTypeAndUserId(Integer id, Integer animalId, String animalType, Integer userId) throws NullPointerException;
	
	Production findById(Integer id) throws NullPointerException;
	
	List<Production> findAllGroupByAnimalIdOrderByEarning(Integer page, Integer size);

	
}
