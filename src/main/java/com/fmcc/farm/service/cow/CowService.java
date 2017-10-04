package com.fmcc.farm.service.cow;

import java.util.List;

import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;

public interface CowService {
	
	Cow create(Cow t);

	void update(Cow t, Integer pathId);

	List<Cow> getAll(Integer userId, Integer page, Integer size);

	void addNewProduction(Production p, Integer animalId);

	Cow findById(Integer id);
	
	Cow findByIdAndUserId(Integer id, Integer userId);

}
