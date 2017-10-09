package com.fmcc.farm.controller.chicken;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.mappers.chicken.ChickenMapper;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.service.chicken.ChickenService;

@RestController
@RequestMapping(value="/user/{user_id}/animal/chicken")
public class ChickenControllerImpl implements ChickenController{
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private ChickenMapper chickenMapper;
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ChickenDTO create(@RequestBody ChickenDTO t, 
								@PathVariable(name="user_id") Integer userId) throws NullPointerException{
		final Chicken c = chickenService.create(chickenMapper.map(t),userId);
		return chickenMapper.map(c);
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED,
					reason = "Delete request not allowed.")
	public void delete(@RequestBody ChickenDTO t) {
		//Nothing to do.		
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED,
					reason = "Delete request not allowed.")
	public void deleteWithId(@RequestBody ChickenDTO t) {
		//Nothing to do.		
	}
	
	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@RequestBody ChickenDTO t, 
						@PathVariable(name="user_id") Integer userId,
						@PathVariable(name="id") Integer id) throws NullPointerException{
		final Chicken c = chickenMapper.map(t);
		chickenService.update(c, id, userId);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ChickenDTO> getAll(@RequestParam(name="page",defaultValue="1") Integer page,
									@RequestParam(name="size",defaultValue="5") Integer size,
									@PathVariable(name="user_id") Integer userId) {
		final List<ChickenDTO> dtos = new ArrayList<>();
		chickenService.getAll(userId,page, size).forEach(c -> { 
			dtos.add(chickenMapper.map(c));
		});
		return dtos;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ChickenDTO findById(@PathVariable(name="id") Integer id,
								@PathVariable(name="user_id") Integer userId) throws NullPointerException {
		return chickenMapper.map(chickenService.findByIdAndUserId(id, userId));
	}

}
