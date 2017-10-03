package com.fmcc.farm.controller.user;

import java.util.List;

import com.fmcc.farm.dto.UserDTO;

public interface UserController {

	UserDTO create(UserDTO t);

	void delete(UserDTO t, Integer id);
	
	void update(UserDTO t, Integer id);

	List<UserDTO> getAll(Integer page, Integer size);

	UserDTO findById(Integer id);

}
