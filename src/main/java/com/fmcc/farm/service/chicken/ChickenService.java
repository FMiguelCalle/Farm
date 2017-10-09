package com.fmcc.farm.service.chicken;

import java.util.List;

import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;

public interface ChickenService {

	Chicken create(Chicken t, Integer userId) throws NullPointerException;
	
	void update(Chicken t, Integer pathId, Integer userId) throws NullPointerException;
	
	List<Chicken> getAll(Integer userId, Integer page, Integer size);

	Chicken findById(Integer id) throws NullPointerException;
	
	void addNewProduction(Production p, Integer animalId, Integer userId) throws NullPointerException;
	
	Chicken findByIdAndUserId(Integer id, Integer userId) throws NullPointerException;

}
