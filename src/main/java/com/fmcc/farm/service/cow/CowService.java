package com.fmcc.farm.service.cow;

import java.util.List;

import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;

public interface CowService {
	
	Cow create(Cow t);

	void update(Cow t);

	List<Cow> getAll(Integer userId, Integer page, Integer size);

	Cow findById(Integer id);
	
	void addNewProduction(Production p, Integer animalId);
	
	List<Cow> findAllByUserId(Integer userId, Integer page, Integer size);
	
}
