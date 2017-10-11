package com.fmcc.farm.controller.statsuserearning;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.service.statsuserearning.StatsUserEarningService;

@RestController
@RequestMapping("/user/stats")
public class StatsUserEarningControllerImpl implements StatsUserEarningController{
	
	@Autowired
	private StatsUserEarningService statsUserEarningService;
	
	@Override
	@RequestMapping(method=RequestMethod.GET)
	public List<StatsUserEarningDTO> usersEarningsBetweenDates(@RequestParam(name="from_date") @DateTimeFormat(pattern="MMddyyyy") Date fromDate,
																	@RequestParam(name="to_date") @DateTimeFormat(pattern="MMddyyyy") Date toDate,
																	@RequestParam(name="page",defaultValue="1") Integer page, 
																	@RequestParam(name="size",defaultValue="5") Integer size) {
		return statsUserEarningService.usersEarningsBetweenDates(fromDate, toDate, page, size);
	}

}
