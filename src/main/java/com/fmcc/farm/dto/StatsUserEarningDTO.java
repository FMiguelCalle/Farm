package com.fmcc.farm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsUserEarningDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736269373264310002L;

	private Integer id;
	
	private Long earning;

	public StatsUserEarningDTO(Integer id, Long earning) {
		this.id = id;
		this.earning = earning;
	}
}
