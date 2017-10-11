package com.fmcc.farm.service.production;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.ProductionDAO;
import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;

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
	private UrlElementsExistValidator urlElementsExistValidator;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private CowService cowService;
	
	@Override
	public Production create(Production t, Integer animalId, String animalType, Integer userId) throws NullPointerException{
		if(urlElementsExistValidator.validateUrlElementsExistence(userId, animalId, animalType)) {
			final Production p = dao.save(t);
			if(animalType.equals("chicken")) {
				chickenService.addNewProduction(t, animalId, userId);
			}else {
				cowService.addNewProduction(t, animalId, userId);
			}
			return p;
		} else {
			return null;
		}
		
	}

	@Override
	public void delete(Production t, Integer pathId, Integer animalId, String animalType, Integer userId) throws NullPointerException{
		if(idValidator.validateMatchingIds(t.getId(), pathId) &&
				urlElementsExistValidator.validateUrlElementsExistence(userId, animalId, animalType, pathId)) {
			dao.delete(t);		
		}
	}

	@Override
	public void update(Production t, Integer pathId, Integer animalId, String animalType, Integer userId) throws NullPointerException{
		if(idValidator.validateMatchingIds(t.getId(), pathId) &&
				urlElementsExistValidator.validateUrlElementsExistence(userId, animalId, animalType, pathId)) {
			dao.save(t);
			if(animalType.equals("chicken")) {
				chickenService.addNewProduction(t, animalId, userId);
			}else {
				cowService.addNewProduction(t, animalId, userId);
			}
		}
	}

	@Override
	public List<Production> getAll(Integer animalId, String animalType, Integer userId, Integer page, Integer size) {
		if(urlElementsExistValidator.validateUrlElementsExistence(userId, animalId, animalType)) {
			final List<Production> productions = new ArrayList<>();
			if(pageAndSizeValidator.validatePageAndSize(page, size)) {
				dao.findAllByAnimalId(animalId, new PageRequest(page-1, size)).forEach(production -> {
					productions.add(production);
				});
			}
			return productions;
		} else {
			return null;
		}
	}

	@Override
	public Production findByIdAndAnimalIdAndAnimalTypeAndUserId(Integer id, Integer animalId, String animalType, Integer userId) throws NullPointerException{		
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
		return null;
	}
	
	@Override
	public Production findById(Integer id) throws NullPointerException{
		return dao.findOne(id);
	}

	@Override
	public List<StatsTopAnimalDTO> findAllGroupByAnimalIdOrderByEarning(Integer userId, Integer n, Integer page, Integer size) {
		List<StatsTopAnimalDTO> dtos = new ArrayList<>();
		if(pageAndSizeValidator.validatePageAndSize(page, n)) {
			dao.topNAnimalsFromUser(userId, new PageRequest(page-1, n)).forEach(a -> {
				dtos.add(a);
			});
		} else if(pageAndSizeValidator.validatePageAndSize(page, size)) {
			dao.topNAnimalsFromUser(userId, new PageRequest(page-1, size)).forEach(a -> {
				dtos.add(a);
			});
		}
		return dtos;
	}
	
}
