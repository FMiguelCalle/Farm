package com.fmcc.farm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.model.Chicken;

@Repository
public interface ChickenDAO extends PagingAndSortingRepository<Chicken, Integer> {

	public Page<Chicken> findAllByUserId(Integer userId,Pageable p);
	
}