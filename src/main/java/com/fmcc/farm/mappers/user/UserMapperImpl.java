package com.fmcc.farm.mappers.user;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.User;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserMapperImpl implements UserMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private CowService cowService;

	@Override
	public UserDTO map(User u) {
	
		final List<Integer> ids = new ArrayList<>(); 
		u.getAnimals().forEach(a -> {
			ids.add(a.getId());
		});
		final User user = u;
		user.setAnimals(new ArrayList<>());
		final UserDTO dto = mapper.map(user, UserDTO.class);
		dto.setAnimals(ids);
		return dto;
	}

	@Override
	public User map(UserDTO u) {
		final List<Animal> animals = new ArrayList<>(); 
		u.getAnimals().forEach(id -> {
			Chicken chicken = chickenService.findById(id);
			if(chicken == null) {
				Cow cow = cowService.findById(id);
				animals.add(cow);
			} else {
				animals.add(chicken);
			}
		});
		log.info(animals.toString());
		final UserDTO dto = u;
		final User user = mapper.map(dto, User.class);
		user.setAnimals(animals);
		return user;
	}	
	
}
