package com.fmcc.farm.controller.production;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmcc.farm.dto.ProductionDTO;
import com.fmcc.farm.mappers.production.ProductionMapper;
import com.fmcc.farm.model.Production;
import com.fmcc.farm.service.chicken.ChickenService;
import com.fmcc.farm.service.cow.CowService;
import com.fmcc.farm.service.production.ProductionService;

@RestController
@RequestMapping(value = "user/{user_id}/animal/{animal_type}/{animal_id}/production")
public class ProductionControllerImpl implements ProductionController {
	
	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private ProductionMapper productionMapper;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private CowService cowService;

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ProductionDTO create(@RequestBody ProductionDTO t,
								@PathVariable(name="animal_id") Integer animalId,
								@PathVariable(name="animal_type") String animalType) {
		final Production p= productionService.create(productionMapper.map(t));
		if(animalType.equals("chicken")) {
			chickenService.addNewProduction(p, animalId);
		}else {
			cowService.addNewProduction(p, animalId);
		}
		return productionMapper.map(p);
	}

	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public void delete(ProductionDTO t, @PathVariable(name="id") Integer id) {
		
		if(t.getId() != null) {
			if(t.getId().equals(id)) {
				productionService.delete(productionMapper.map(t));
			}
			else {
				throw new NullPointerException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@RequestBody ProductionDTO t,
						@PathVariable(name="animal_id") Integer animalId,
						@PathVariable(name="animal_type") String animalType,
						@PathVariable(name="id") Integer id) {
		if(t.getId() != null) {
			if(t.getId().equals(id)) {
				final Production p = productionMapper.map(t);
				productionService.update(p);
				if(animalType.equals("chicken")) {
					chickenService.addNewProduction(p, animalId);
				}else {
					cowService.addNewProduction(p, animalId);
				}
			} else {
				throw new NullPointerException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductionDTO> getAll(@PathVariable(name="animal_id") Integer animalId, 
										@RequestParam(name="page",defaultValue="1") Integer page, 
										@RequestParam(name="size",defaultValue="5") Integer size) {
		List<ProductionDTO> dtos = new ArrayList<>();
		if(page > 0 && size < 11 && size > 0) {
			productionService.getAll(animalId,page-1,size).forEach(prod -> {
				dtos.add(productionMapper.map(prod));
			});	
		}
		return dtos;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public ProductionDTO findById(@PathVariable Integer id){
		return productionMapper.map(productionService.findById(id));
	}
	
	
}
