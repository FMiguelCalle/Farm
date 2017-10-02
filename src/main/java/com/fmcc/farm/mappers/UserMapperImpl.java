package com.fmcc.farm.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.User;

@Component
public class UserMapperImpl implements UserMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private AnimalMapper animalMapper;

	@Override
	public UserDTO map(User u) {
	
		final List<Integer> ids = animalMapper.createListIdFromAnimal(u.getAnimals());
		final User user = u;
		user.setAnimals(new ArrayList<>());
		final UserDTO dto = mapper.map(user, UserDTO.class);
		dto.setAnimals(ids);
		return dto;
	}

	@Override
	public User map(UserDTO u) {
		final List<Animal> animals = animalMapper.createListAnimalFromId(u.getAnimals());
		final UserDTO dto = u;
		final User user = mapper.map(dto, User.class);
		user.setAnimals(animals);
		return user;
	}	
	
}
