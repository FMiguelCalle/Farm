package com.fmcc.farm.service.production;

import java.util.List;

import com.fmcc.farm.model.Production;

public interface ProductionService {

	Production create(Production t);

	void delete(Production t);

	void update(Production t);

	List<Production> getAll(Integer animalId, Integer page, Integer size);

	Production findById(Integer id);
	
	List<Production> findAllGroupByAnimalIdOrderByEarning(Integer page, Integer size);
}
