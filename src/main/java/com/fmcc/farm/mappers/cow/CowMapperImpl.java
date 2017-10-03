package com.fmcc.farm.mappers.cow;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmcc.farm.dto.CowDTO;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.model.Cow;
import com.fmcc.farm.model.Production;

@Component
public class CowMapperImpl implements CowMapper{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ProductionMapper productionMapper;

	@Override
	public CowDTO map(Cow c) {
		final List<Integer> ids = productionMapper.createListIdFromProduction(c.getProductions());
		final Cow cow = c;
		cow.setProductions(new ArrayList<Production>());
		final CowDTO dto = mapper.map(cow, CowDTO.class);
		dto.setProductions(ids);
		return dto;
	}

	@Override
	public Cow map(CowDTO c) {
		final List<Production> productions = productionMapper.createListProductionFromId(c.getProductions());
		final Cow cow = mapper.map(c, Cow.class);
		cow.setProductions(productions);
		return cow	;
	}
	
	
}
