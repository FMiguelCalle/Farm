package com.fmcc.farm.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.ChickenDTO;
import com.fmcc.farm.model.Chicken;
import com.fmcc.farm.model.Production;

@Component
public class ChickenMapperImpl implements ChickenMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ProductionMapper productionMapper;
	

	@Override
	public ChickenDTO map(Chicken c) {
		final List<Integer> ids = productionMapper.createListIdFromProduction(c.getProductions());
		final Chicken chicken = c;
		chicken.setProductions(new ArrayList<Production>());
		final ChickenDTO dto = mapper.map(chicken, ChickenDTO.class);
		dto.setProductions(ids);
		return dto;
	}

	@Override
	public Chicken map(ChickenDTO c) {
		
		final List<Production> productions = productionMapper.createListProductionFromId(c.getProductions());
		final Chicken chicken = mapper.map(c, Chicken.class);
		chicken.setProductions(productions);
		return chicken;
	}
}
