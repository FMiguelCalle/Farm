package com.fmcc.farm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dao.AnimalDAO;
import com.fmcc.farm.model.Animal;

@Service
public class AnimalServiceImpl implements AnimalService{

	@Autowired
	private AnimalDAO dao;
	
	@Override
	public List<Animal> findAllByUserId(Integer userId, Integer page, Integer size) {
		final List<Animal> animals = new ArrayList<>();
		dao.findAllByUserId(userId, new PageRequest(page, size)).forEach(animal -> {
			animals.add(animal);
		});
		return animals;
	}
	
	@Override
	public void update(Animal a) {
		dao.save(a);		
	}

	@Override
	public Animal findById(Integer id) {
		return dao.findOne(id);
	}

}
