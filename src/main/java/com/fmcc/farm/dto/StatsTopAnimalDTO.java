package com.fmcc.farm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsTopAnimalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829804689897943913L;
	
	private Integer id;
	
	private Long profit;
	
	public StatsTopAnimalDTO(Integer id, Long profit) {
		this.id = id;
		this.profit = profit;
	}

}
