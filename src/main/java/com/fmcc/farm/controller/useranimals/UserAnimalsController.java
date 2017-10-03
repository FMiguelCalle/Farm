package com.fmcc.farm.controller.useranimals;

import com.fmcc.farm.dto.UserAnimalsDTO;

public interface UserAnimalsController {
	
	UserAnimalsDTO getAll(Integer page, Integer size, Integer userId);
	
}
