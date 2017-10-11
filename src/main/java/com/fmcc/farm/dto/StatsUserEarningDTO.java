package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class StatsUserEarningDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -134757272022696572L;

	protected Date firstDate;
	
	protected Date lastDate;

}
