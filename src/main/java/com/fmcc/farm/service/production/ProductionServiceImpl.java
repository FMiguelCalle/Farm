package com.fmcc.farm.service.production;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.ProductionDAO;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;

@Service
public class ProductionServiceImpl implements ProductionService{

	@Autowired
	private ProductionDAO dao;
	
	@Autowired
	private PageAndSizeValidator pageAndSizeValidator;

	@Autowired
	private PathIdAndDTOIdMatchValidator idValidator;
	
	@Autowired
	private NotNullValidator notNullValidator;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private CowService cowService;
	
	@Override
	public Production create(Production t, Integer animalId, String animalType, Integer userId) {
		final Production p = dao.save(t);
		if(animalType.equals("chicken")) {
			chickenService.addNewProduction(p, animalId, userId);
		}else {
			cowService.addNewProduction(p, animalId, userId);
		}
		return p;
	}

	@Override
	public void delete(Production t, Integer pathId) {
		if(idValidator.validateMatchingIds(t.getId(), pathId)) {
			dao.delete(t);		
		}
	}

	@Override
	public void update(Production t, Integer pathId, Integer animalId, String animalType, Integer userId) {
		if(idValidator.validateMatchingIds(t.getId(), pathId)) {
			Production p = dao.save(t);
			if(animalType.equals("chicken")) {
				chickenService.addNewProduction(p, animalId, userId);
			}else {
				cowService.addNewProduction(p, animalId, userId);
			}
		}
	}

	@Override
	public List<Production> getAll(Integer animalId, Integer page, Integer size) {
		final List<Production> productions = new ArrayList<>();
		if(pageAndSizeValidator.validatePageAndSize(page, size)) {
			dao.findAllByAnimalId(animalId, new PageRequest(page-1, size)).forEach(production -> {
				productions.add(production);
			});
		}
		return productions;
	}

	@Override
	public Production findByIdAndAnimalIdAndAnimalTypeAndUserId(Integer id, Integer animalId, String animalType, Integer userId) {		
		if(animalType.equals("chicken")) {
			chickenService.findByIdAndUserId(animalId, userId);
			Production p = dao.findByIdAndAnimalId(id, animalId);
			if(notNullValidator.validateNotNull(p)) {
				return p;
			}
		} else {
			if(animalType.equals("cow")){
				cowService.findByIdAndUserId(animalId, userId);
				Production p = dao.findByIdAndAnimalId(id, animalId);
				if(notNullValidator.validateNotNull(p)) {
					return p;
				}
			}
		}
		return new Production();
	}
	
	@Override
	public Production findById(Integer id) {		
		return dao.findOne(id);
	}

	@Override
	public List<Production> findAllGroupByAnimalIdOrderByEarning(Integer page, Integer size) {
		return dao.findAllGroupByAnimalIdOrderByEarning();
	}
	
}
