package com.fmcc.farm.mappers;

import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.model.Cow;

public interface CowMapper {
	
	CowDTO map(Cow c);
	
	Cow map(CowDTO c);

}
