package com.fmcc.farm.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
public class Chicken extends Animal{
	
	private String type;
	
	private String frecuency;
}
