package com.fmcc.farm.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProductionDTO implements Serializable{

	private static final long serialVersionUID = 963175513388098910L;
	
	private Integer id;
	
	private Date productionDate;
	
	private Integer purchasePrice;
	
	private Integer sellingPrice;
	
}
