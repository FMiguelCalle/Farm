package com.fmcc.farm.validators.urlelementexist;

public interface UrlElementsExistValidator {
		
	Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType);

	Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType, Integer productionId);
}
