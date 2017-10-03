package com.fmcc.farm.mappers.production;

import java.util.List;

import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.model.Production;

public interface ProductionMapper {
	
	ProductionDTO map(Production p);
	
	Production map(ProductionDTO p);
	
	List<Integer> createListIdFromProduction(List<Production> productions);
	
	List<Production> createListProductionFromId(List<Integer> ids);

}
