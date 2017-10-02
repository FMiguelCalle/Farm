package com.fmcc.farm.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StatsTopAnimalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829804689897943913L;
	
	private Integer id;
	
	private Integer profit;

}
