package com.fmcc.farm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.model.Production;

@Repository
public interface ProductionDAO extends PagingAndSortingRepository<Production, Integer>{

	public Page<Production> findAllByAnimalId(Integer animalId, Pageable p);

	public Production findByIdAndAnimalId(Integer id, Integer animalId);
	
	@Query(value="select sum(p.sellingPrice-p.purchasePrice),p.animalId from Production as p group by p.animalId")
	public List<Production> findAllGroupByAnimalIdOrderByEarning();
}
