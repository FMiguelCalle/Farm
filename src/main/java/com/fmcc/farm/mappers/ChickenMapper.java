package com.fmcc.farm.mappers;

import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.model.Chicken;

public interface ChickenMapper {
	
	ChickenDTO map(Chicken c);
	
	Chicken map(ChickenDTO c);

}
