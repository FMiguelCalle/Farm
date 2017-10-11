package com.fmcc.farm.service.statsuserearning;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.service.user.UserService;

@Service
public class StatsUserEarningServiceImpl implements StatsUserEarningService {

	@Autowired
	private UserService userService;
	
	@Override
	public List<StatsUserEarningDTO> usersEarningsBetweenDates(Date fromDate, Date toDate, Integer page,
			Integer size) {
		return userService.usersEarningsBetweenDates(fromDate, toDate, page, size);
	}

}
