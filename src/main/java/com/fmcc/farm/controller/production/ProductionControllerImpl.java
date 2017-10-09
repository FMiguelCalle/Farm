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
import com.fmcc.farm.service.production.ProductionService;

@RestController
@RequestMapping(value = "user/{user_id}/animal/{animal_type}/{animal_id}/production")
public class ProductionControllerImpl implements ProductionController {
	
	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private ProductionMapper productionMapper;

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ProductionDTO create(@RequestBody ProductionDTO t,
								@PathVariable(name="animal_id") Integer animalId,
								@PathVariable(name="animal_type") String animalType,
								@PathVariable(name="user_id") Integer userId) throws NullPointerException {
		final Production p = productionService.create(productionMapper.map(t), animalId, animalType, userId);
		return productionMapper.map(p);
	}

	
	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public void delete(ProductionDTO t, 
						@PathVariable(name="id") Integer id,
						@PathVariable(name="animal_id") Integer animalId,
						@PathVariable(name="animal_type") String animalType,
						@PathVariable(name="user_id") Integer userId) throws NullPointerException {
		productionService.delete(productionMapper.map(t),id,animalId,animalType,userId);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@RequestBody ProductionDTO t,
						@PathVariable(name="animal_id") Integer animalId,
						@PathVariable(name="animal_type") String animalType,
						@PathVariable(name="id") Integer id,
						@PathVariable(name="user_id") Integer userId) throws NullPointerException{
		final Production p = productionMapper.map(t);
		productionService.update(p,id,animalId,animalType,userId);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductionDTO> getAll(@PathVariable(name="animal_id") Integer animalId,
										@PathVariable(name="animal_type") String animalType,
										@PathVariable(name="user_id") Integer userId,
										@RequestParam(name="page",defaultValue="1") Integer page, 
										@RequestParam(name="size",defaultValue="5") Integer size) {
		List<ProductionDTO> dtos = new ArrayList<>();
		productionService.getAll(animalId,animalType,userId,page,size).forEach(prod -> {
			dtos.add(productionMapper.map(prod));
		});	
		return dtos;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public ProductionDTO findById(@PathVariable Integer id, 
									@PathVariable(name="animal_id") Integer animalId,
									@PathVariable(name="animal_type") String animalType,
									@PathVariable(name="user_id") Integer userId) throws NullPointerException{
		return productionMapper.map(productionService.findByIdAndAnimalIdAndAnimalTypeAndUserId(id, animalId, animalType, userId));
	}
	
}
