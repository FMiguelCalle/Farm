package com.fmcc.farm.mappers.production;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.production.ProductionService;

@Component
public class ProductionMapperImpl implements ProductionMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ProductionService productionService;
	
	@Override
	public ProductionDTO map(Production p) {
		return mapper.map(p, ProductionDTO.class); 
	}

	@Override
	public Production map(ProductionDTO p) {
		return mapper.map(p, Production.class);
	}
	
	public List<Integer> createListIdFromProduction(List<Production> productions){
		final List<Integer> ids = new ArrayList<>();
		productions.forEach(p -> {
			ids.add(p.getId());
		});
		return ids;
	}
	
	public List<Production> createListProductionFromId(List<Integer> ids) {
		final List<Production> productions = new ArrayList<>();
		ids.forEach(id -> {
			productions.add(productionService.findById(id));
		});
		return productions;
	}

}
