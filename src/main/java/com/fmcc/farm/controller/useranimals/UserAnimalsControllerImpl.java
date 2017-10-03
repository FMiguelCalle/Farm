package com.fmcc.farm.controller.useranimals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.dto.UserAnimalsDTO;
import com.fmcc.farm.mappers.chicken.ChickenMapper;
import com.fmcc.farm.mappers.cow.CowMapper;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;

@RestController
@RequestMapping("/user/{user_id}/animal")
public class UserAnimalsControllerImpl implements UserAnimalsController{
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private ChickenMapper chickenMapper;
	
	@Autowired
	private CowService cowService;
	
	@Autowired
	private CowMapper cowMapper;
	
	@Override
	@RequestMapping(method=RequestMethod.GET)
	public UserAnimalsDTO getAll(@RequestParam(name="page",defaultValue="1") Integer page,
									@RequestParam(name="size",defaultValue="5") Integer size, 
									@PathVariable(name="user_id") Integer userId) {
		final List<ChickenDTO> chickens = new ArrayList<>();
		chickenService.findAllByUserId(userId, page-1, size).forEach(c -> {
			chickens.add(chickenMapper.map(c));
		});
		final List<CowDTO> cows = new ArrayList<>();
		cowService.findAllByUserId(userId, page-1, size).forEach(c -> {
			cows.add(cowMapper.map(c));
		});
		return new UserAnimalsDTO(chickens, cows);
	}

}
