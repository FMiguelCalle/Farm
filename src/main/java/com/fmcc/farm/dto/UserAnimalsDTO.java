package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnimalsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211348733825489828L;

	private List<ChickenDTO> chickens;
	
	private List<CowDTO> cows;
	
}
