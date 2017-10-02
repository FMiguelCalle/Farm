package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class StatsUserEarningDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1673673857667033587L;
	
	private Integer id;
	
	private Date firstDate;
	
	private Date lastDate;
	
	private Integer earning;

}
