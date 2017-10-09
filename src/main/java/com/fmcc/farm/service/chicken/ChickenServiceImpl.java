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
	private UserService userService;
	
	@Override
	public Chicken create(Chicken t, Integer userId) {	
		final Chicken chicken = dao.save(t);
		userService.addNewAnimal(t, userId);
		return chicken;
	}

	@Override
	public void update(Chicken t, Integer pathId, Integer userId) {
		if(idValidator.validateMatchingIds(t.getId(), pathId)) {
			dao.save(t);
			userService.addNewAnimal(t, userId);
		}
	}

	@Override
	public List<Chicken> getAll(Integer userId, Integer page, Integer size) {
		final List<Chicken> chickens = new ArrayList<>();
		if(pageAndSizeValidator.validatePageAndSize(page, size)) {
			dao.findAllByUserId(userId, new PageRequest(page-1, size)).forEach(chicken -> {
				chickens.add(chicken);
			});
		}
		return chickens;
	}

	@Override
	public Chicken findById(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public void addNewProduction(Production p, Integer animalId, Integer userId) {
		Chicken c = findById(animalId);
		final List<Production> productions = c.getProductions();
		productions.add(p);
		c.setProductions(productions);
		update(c,animalId,userId);
	}

	@Override
	public Chicken findByIdAndUserId(Integer id, Integer userId) {
		Chicken chicken = dao.findByIdAndUserId(id, userId);
		if(notNullValidator.validateNotNull(chicken)) {
			return chicken;
		}else {
			return new Chicken();
		}
	}

}
