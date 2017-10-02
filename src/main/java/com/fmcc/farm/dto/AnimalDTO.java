package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class AnimalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211348733825489828L;

	private Integer id;
	
	private List<Integer> productions;
	
}
