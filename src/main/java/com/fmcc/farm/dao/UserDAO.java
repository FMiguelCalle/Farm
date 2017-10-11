package com.fmcc.farm.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fmcc.farm.dto.StatsUserEarningDTO;
import com.fmcc.farm.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Integer> {
	
	@Query(value="SELECT new com.fmcc.farm.dto.StatsUserEarningDTO(a.userId, sum(p.sellingPrice-p.purchasePrice) AS earning) FROM Animal AS a, Production AS p WHERE a.id = p.animalId AND p.productionDate BETWEEN :firstDate AND :lastDate GROUP BY a.userId")
	public List<StatsUserEarningDTO> usersEarningsBetweenDates(@Param(value="firstDate") Date firstDate,
																	@Param(value="lastDate") Date lastDate);
}
