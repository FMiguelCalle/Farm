package com.fmcc.farm.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.mappers.user.UserMapper;
import com.fmcc.farm.service.user.UserService;

@RestController
@RequestMapping(value="/user")
public class UserControllerImpl implements UserController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO create(@RequestBody UserDTO t) throws NullPointerException{
		return userMapper.map(userService.create(userMapper.map(t)));
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public void delete(@RequestBody UserDTO t, @PathVariable(name="id") Integer id) throws NullPointerException{
		userService.delete(userMapper.map(t),id);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@RequestBody UserDTO t, @PathVariable(name="id") Integer id) throws NullPointerException{
		userService.update(userMapper.map(t),id);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> getAll(@RequestParam(name="page",defaultValue="1") Integer page,@RequestParam(name="size",defaultValue="5") Integer size) {
		final List<UserDTO> dtos = new ArrayList<>();
		userService.getAll(page,size).forEach(u -> {
			dtos.add(userMapper.map(u));
		});
		return dtos;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserDTO findById(@PathVariable Integer id) throws NullPointerException {
		return userMapper.map(userService.findById(id));
	}

}
