package com.fmcc.farm.controller.user;

import java.util.List;

import com.fmcc.farm.dto.UserDTO;

public interface UserController {

	UserDTO create(UserDTO t) throws NullPointerException;

	void delete(UserDTO t, Integer id) throws NullPointerException;
	
	void update(UserDTO t, Integer id) throws NullPointerException;

	List<UserDTO> getAll(Integer page, Integer size);

	UserDTO findById(Integer id) throws NullPointerException;

}
