package com.fmcc.farm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.model.Animal;

@Repository
public interface AnimalDAO extends PagingAndSortingRepository<Animal, Integer>{

	public Page<Animal> findAllByUserId(Integer userId, Pageable p);

	public List<Animal> findAllByUserId(Integer userId);
}