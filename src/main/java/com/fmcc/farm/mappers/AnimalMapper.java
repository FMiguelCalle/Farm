package com.fmcc.farm.mappers;

import java.util.List;

import com.fmcc.farm.dto.AnimalDTO;
import com.fmcc.farm.model.Animal;

public interface AnimalMapper {
	
	AnimalDTO map(Animal u);
	
	Animal map (AnimalDTO dto);
	
	List<Integer> createListIdFromAnimal(List<Animal> animals);

	List<Animal> createListAnimalFromId(List<Integer> ids);
	
}
