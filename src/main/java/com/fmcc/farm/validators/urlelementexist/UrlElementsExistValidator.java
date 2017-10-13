package com.fmcc.farm.validators.urlelementexist;

public interface UrlElementsExistValidator {
		
	Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType) throws NullPointerException;

	Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType, Integer productionId) throws NullPointerException;
}
