package com.fmcc.farm.service.cow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.CowDAO;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.user.UserService;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;
import com.fmcc.farm.validators.urlelementexist.UrlElementsExistValidator;

@Service
public class CowServiceImpl implements CowService{
	
	@Autowired
	private CowDAO dao;
	
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
	public Cow create(Cow t, Integer userId) throws NullPointerException{
		if(notNullValidator.validateNotNull(userService.findById(userId))) {
			final Cow cow = dao.save(t);
			userService.addNewAnimal(t, userId);
			return cow;
		} else {
			return null;
		}
	}

	@Override
	public void update(Cow t, Integer pathId, Integer userId) throws NullPointerException {
		if(idValidator.validateMatchingIds(t.getId(), pathId) &&
				urlElementsExistValidator.validateUrlElementsExistence(userId, pathId,"cow")) {
			dao.save(t);
			userService.addNewAnimal(t, userId);
		}
	}

	@Override
	public List<Cow> getAll(Integer userId, Integer page, Integer size) {
		if(notNullValidator.validateNotNull(userService.findById(userId))) {
			final List<Cow> cows = new ArrayList<>();
			if(pageAndSizeValidator.validatePageAndSize(page, size)) {
				dao.findAllByUserId(userId, new PageRequest(page-1, size)).forEach(cow -> {
					cows.add(cow);
				});
			}
			return cows;
		} else {
			return null;
		}
	}

	@Override
	public Cow findById(Integer id) throws NullPointerException{
		return dao.findOne(id);
	}
	
	@Override
	public Cow findByIdAndUserId(Integer id, Integer userId) throws NullPointerException{
		Cow cow = dao.findByIdAndUserId(id, userId);
		if(notNullValidator.validateNotNull(cow)) {
			return cow;
		} else {
			return null;
		}
	}

	@Override
	public void addNewProduction(Production p, Integer animalId, Integer userId) throws NullPointerException {
		Cow c = findById(animalId);
		if(notNullValidator.validateNotNull(c)) {
			final List<Production> productions = c.getProductions();
			productions.add(p);
			c.setProductions(productions);
			update(c, animalId, userId);
		}
	}

}
