package com.fmcc.farm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Integer> {
	
}
