package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CowDTO implements Serializable{
	
	private static final long serialVersionUID = 2489651554929169090L;
	
	private Integer id;
	
	private String breed;
	
	private List<Integer> productions;

}
