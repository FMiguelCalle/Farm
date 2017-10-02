package com.fmcc.farm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Production {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false,updatable=false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="production_date",nullable = false)
	private Date productionDate;
	
	@Column(nullable = false,name="purchase_price")
	private Integer purchasePrice;
	
	@Column(nullable = false,name="selling_price")
	private Integer sellingPrice;
	
	@Column(name="animal_id")
	private Integer animalId;


}
