package com.fmcc.farm.service.chicken;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.ChickenDAO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;

@Service
public class ChickenServiceImpl implements ChickenService{
	
	@Autowired
	private ChickenDAO dao;
	
	@Autowired
	private PageAndSizeValidator pageAndSizeValidator;
	
	@Autowired
	private PathIdAndDTOIdMatchValidator idValidator;
	
	@Autowired
	private NotNullValidator notNullValidator;
	
	@Autowired
	private UrlElementsExistValidator urlElementsExistValidator;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Chicken create(Chicken t, Integer userId) throws NullPointerException{	
		if(notNullValidator.validateNotNull(userService.findById(userId))) {
			final Chicken chicken = dao.save(t);
			userService.addNewAnimal(t, userId);
			return chicken;
		} else {
			return null;
		}
		
	}

	@Override
	public void update(Chicken t, Integer pathId, Integer userId) throws NullPointerException{
		if(idValidator.validateMatchingIds(t.getId(), pathId) &&
				urlElementsExistValidator.validateUrlElementsExistence(userId, pathId, "chicken")) {
			dao.save(t);
			userService.addNewAnimal(t, userId);
		}
	}

	@Override
	public List<Chicken> getAll(Integer userId, Integer page, Integer size) {
		if(notNullValidator.validateNotNull(userService.findById(userId))) {
			final List<Chicken> chickens = new ArrayList<>();
			if(pageAndSizeValidator.validatePageAndSize(page, size)) {
				dao.findAllByUserId(userId, new PageRequest(page-1, size)).forEach(chicken -> {
					chickens.add(chicken);
				});
			}
			return chickens;
		} else {
			return null;
		}
	}

	@Override
	public Chicken findById(Integer id){
		return dao.findOne(id);
	}

	@Override
	public void addNewProduction(Production p, Integer animalId, Integer userId) throws NullPointerException{
		Chicken c = findById(animalId);
		if(notNullValidator.validateNotNull(c)) {
			final List<Production> productions = c.getProductions();
			productions.add(p);
			c.setProductions(productions);
			update(c,animalId,userId);
		}
	}

	@Override
	public Chicken findByIdAndUserId(Integer id, Integer userId) throws NullPointerException{
		Chicken chicken = dao.findByIdAndUserId(id, userId);
		if(notNullValidator.validateNotNull(chicken)) {
			return chicken;
		}else {
			return null;
		}
	}

}
