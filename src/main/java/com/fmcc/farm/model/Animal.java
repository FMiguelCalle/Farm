package com.fmcc.farm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data 
@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public abstract class Animal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable= false, updatable=false)
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinColumn(name="animal_id")
	private List<Production> productions;
	
	@Column(name="user_id")
	private Integer userId;
	
}
