package com.fmcc.farm.controller.cow;

import java.util.List;

import com.fmcc.farm.dto.CowDTO;

public interface CowController {

	CowDTO create(CowDTO t, Integer userId);

	void delete(CowDTO t);
	
	void update(CowDTO t, Integer userId, Integer id);

	List<CowDTO> getAll(Integer page, Integer size, Integer userId);

	CowDTO findById(Integer id);

}
