package com.fmcc.farm.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
public class Cow extends Animal{
	
	private String breed;

}
