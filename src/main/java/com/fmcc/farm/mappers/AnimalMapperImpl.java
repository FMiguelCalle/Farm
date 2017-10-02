package com.fmcc.farm.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.AnimalDTO;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.AnimalService;

@Component
public class AnimalMapperImpl implements AnimalMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ProductionMapper productionMapper;
	
	@Override
	public AnimalDTO map(Animal a) {
		final List<Integer> ids = productionMapper.createListIdFromProduction(a.getProductions());
		final Animal animal = a;
		animal.setProductions(new ArrayList<Production>());
		final AnimalDTO dto = mapper.map(animal, AnimalDTO.class);
		dto.setProductions(ids);
		return dto;
	}

	@Override
	public Animal map(AnimalDTO dto) {
		final List<Production> productions = productionMapper.createListProductionFromId(dto.getProductions());
		final Animal animal = mapper.map(dto, Animal.class);
		animal.setProductions(productions);
		return animal;
	}

	public List<Integer> createListIdFromAnimal(List<Animal> animals){
		final List<Integer> ids = new ArrayList<>();
		animals.forEach(a -> {
			ids.add(a.getId());
		});
		return ids;
	}
	
	public List<Animal> createListAnimalFromId(List<Integer> ids) {
		List<Animal> animals = new ArrayList<>();
		ids.forEach(id -> {
			animals.add(animalService.findById(id));
		});
		return animals;
	}
}
