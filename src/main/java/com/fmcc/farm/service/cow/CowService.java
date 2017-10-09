package com.fmcc.farm.service.cow;

import java.util.List;

import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;

public interface CowService {
	
	Cow create(Cow t, Integer userId) throws NullPointerException;

	void update(Cow t, Integer pathId, Integer userId) throws NullPointerException;
	
	List<Cow> getAll(Integer userId, Integer page, Integer size);

	void addNewProduction(Production p, Integer animalId, Integer userId) throws NullPointerException;
	
	Cow findById(Integer id) throws NullPointerException;
	
	Cow findByIdAndUserId(Integer id, Integer userId) throws NullPointerException;

}
