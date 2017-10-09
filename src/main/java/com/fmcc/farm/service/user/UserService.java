package com.fmcc.farm.service.user;

import java.util.List;

import com.fmcc.farm.model.Animal;
import com.fmcc.farm.model.User;

public interface UserService{

	User create(User t) throws NullPointerException;

	void delete(User t, Integer pathId) throws NullPointerException;

	void update(User t, Integer pathId) throws NullPointerException;

	List<User> getAll(Integer page, Integer size);

	User findById(Integer id) throws NullPointerException;

	void addNewAnimal(Animal a, Integer userId) throws NullPointerException;
		
}
