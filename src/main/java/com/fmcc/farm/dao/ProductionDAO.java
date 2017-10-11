package com.fmcc.farm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.dto.StatsTopAnimalDTO;
import com.fmcc.farm.model.Production;

@Repository
public interface ProductionDAO extends PagingAndSortingRepository<Production, Integer>{

	public Page<Production> findAllByAnimalId(Integer animalId, Pageable p);

	public Production findByIdAndAnimalId(Integer id, Integer animalId);
	
	@Query(value="SELECT new com.fmcc.farm.dto.StatsTopAnimalDTO(p.animalId,sum(p.sellingPrice-p.purchasePrice) AS profit) FROM Production AS p WHERE p.animalId IN (SELECT animal.id FROM Animal AS animal WHERE animal.userId = :userId) GROUP BY p.animalId ORDER BY profit DESC")
	public Page<StatsTopAnimalDTO> topNAnimalsFromUser(@Param(value="userId") Integer userId, Pageable p);
}
