package com.fmcc.farm.service.cow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.CowDAO;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.validators.dtoidpathid.PathIdAndDTOIdMatchValidator;
import com.fmcc.farm.validators.notnull.NotNullValidator;
import com.fmcc.farm.validators.pagesize.PageAndSizeValidator;

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

	@Override
	public Cow create(Cow t) {
		return dao.save(t);
	}

	@Override
	public void update(Cow t, Integer pathId) {
		if(idValidator.validateMatchingIds(t.getId(), pathId)) {
			dao.save(t);	
		}
	}

	@Override
	public List<Cow> getAll(Integer userId, Integer page, Integer size) {
		final List<Cow> cows = new ArrayList<>();
		if(pageAndSizeValidator.validatePageAndSize(page, size)) {
			dao.findAllByUserId(userId, new PageRequest(page-1, size)).forEach(cow -> {
				cows.add(cow);
			});
		}
		return cows;
	}

	@Override
	public Cow findById(Integer id) {
		return dao.findOne(id);
	}
	
	@Override
	public Cow findByIdAndUserId(Integer id, Integer userId) {
		Cow cow = dao.findByIdAndUserId(id, userId);
		if(notNullValidator.validateNotNull(cow)) {
			return cow;
		} else {
			return new Cow();
		}
	}

	@Override
	public void addNewProduction(Production p, Integer animalId) {
		Cow c = findById(animalId);
		final List<Production> productions = c.getProductions();
		productions.add(p);
		c.setProductions(productions);
		update(c,animalId);
	}

}
