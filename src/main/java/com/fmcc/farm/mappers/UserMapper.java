package com.fmcc.farm.mappers;

import com.fmcc.farm.dto.UserDTO;
import com.fmcc.farm.model.User;

public interface UserMapper {
	
	UserDTO map(User u);
	
	User map(UserDTO u);
	
}
