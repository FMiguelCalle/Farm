package com.fmcc.farm.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageErrorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257525305800848650L;
	
	private String msg;
	
}
