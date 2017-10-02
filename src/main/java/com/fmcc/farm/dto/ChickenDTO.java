package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ChickenDTO implements Serializable{

	private static final long serialVersionUID = -4347004055766384421L;

	private Integer id;
	
	private String type;
	
	private String frecuency;
	
	private List<Integer> productions;
	
}
