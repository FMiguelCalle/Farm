package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

	private static final long serialVersionUID = -2428291933083313862L;
	
	private Integer id;
	
	private String username;
	
	private List<Integer> animals;
}
