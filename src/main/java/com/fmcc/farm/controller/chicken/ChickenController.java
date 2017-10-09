package com.fmcc.farm.controller.chicken;

import java.util.List;

import com.fmcc.farm.dto.ChickenDTO;

public interface ChickenController {

	ChickenDTO create(ChickenDTO t, Integer userId) throws NullPointerException;

	void delete(ChickenDTO t);
	
	void update(ChickenDTO t, Integer userId, Integer id) throws NullPointerException;

	List<ChickenDTO> getAll(Integer page, Integer size, Integer userId);

	ChickenDTO findById(Integer id, Integer userId) throws NullPointerException;


}
