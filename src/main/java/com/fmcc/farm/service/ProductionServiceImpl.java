package com.fmcc.farm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.ProductionDAO;
import com.fmcc.farm.model.Production;

@Service
public class ProductionServiceImpl implements ProductionService{

	@Autowired
	private ProductionDAO dao;

	@Override
	public Production create(Production t) {
		return dao.save(t);
	}

	@Override
	public void delete(Production t) {
		dao.delete(t);		
	}

	@Override
	public void update(Production t) {
		dao.save(t);
	}

	@Override
	public List<Production> getAll(Integer animalId, Integer page, Integer size) {
		final List<Production> productions = new ArrayList<>();
		dao.findAllByAnimalId(animalId, new PageRequest(page, size)).forEach(production -> {
			productions.add(production);
		});
		return productions;
	}

	@Override
	public Production findById(Integer id){
		return dao.findOne(id);
	}

	@Override
	public List<Production> findAllGroupByAnimalIdOrderByEarning(Integer page, Integer size) {
		return dao.findAllGroupByAnimalIdOrderByEarning();
	}
	
}
