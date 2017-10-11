package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsUserEarningDTOPost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736269373264310002L;

	private Integer id;
	
	private Long earning;

	public StatsUserEarningDTOPost(Integer id, Date firstDate, Date lastDate, Long earning) {
		this.id = id;
		this.earning = earning;
	}
}
