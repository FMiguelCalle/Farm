package com.fmcc.farm.controller.production;

import java.util.List;

import com.fmcc.farm.dto.ProductionDTO;

public interface ProductionController {

	ProductionDTO create(ProductionDTO t, Integer animalId, String animalType, Integer userId);

	void update(ProductionDTO t, Integer animalId, String animalType, Integer id, Integer userId);
	
	void delete(ProductionDTO t, Integer id);
	
	List<ProductionDTO> getAll(Integer animalId, Integer page, Integer size);

	ProductionDTO findById(Integer id, Integer animalId, String animalType, Integer userId);


}
