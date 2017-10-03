package com.fmcc.farm.service.user;

import java.util.List;

import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.User;

public interface UserService{

	User create(User t);

	void delete(User t);

	void update(User t);

	List<User> getAll(Integer page, Integer size);

	User findById(Integer id);

	void addNewAnimal(Animal a, Integer userId);
		
}
