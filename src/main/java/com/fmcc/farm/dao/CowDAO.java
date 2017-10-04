package com.fmcc.farm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.model.Cow;

@Repository
public interface CowDAO extends PagingAndSortingRepository<Cow, Integer>{

	public Page<Cow> findAllByUserId(Integer userId,Pageable p);
	
	public Cow findByIdAndUserId(Integer id, Integer userId);

}
