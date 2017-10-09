package com.fmcc.farm.validators.urlelementexist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.production.ProductionService;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.validators.notnull.NotNullValidator;

@Component
public class UrlElementsExistValidatorImpl implements UrlElementsExistValidator {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private CowService cowService;
	
	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private NotNullValidator notNullValidator;
	
	@Override
	public Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType) {
		if(notNullValidator.validateNotNull(userService.findById(userId))) {
			if(animalType.equals("chicken")) {
				if(notNullValidator.validateNotNull(chickenService.findByIdAndUserId(animalId, userId))) {
					return new Boolean(true);
				} else {
					return new Boolean(false);
				}
			} else if (animalType.equals("cow")) {
				if(notNullValidator.validateNotNull(cowService.findByIdAndUserId(animalId, userId))) {
					return new Boolean(true);
				} else {
					return new Boolean(false);
				}
			} else {
				return new Boolean(false);
			}
		} else {
			return new Boolean(false);
		}
	}

	@Override
	public Boolean validateUrlElementsExistence(Integer userId, Integer animalId, String animalType, Integer productionId) {
		if(validateUrlElementsExistence(userId, animalId, animalType) &&
				notNullValidator.validateNotNull(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(productionId, animalId, animalType, userId))) {
			return new Boolean(true);
		} else {
			return new Boolean(false);
		}
	}
	
}
