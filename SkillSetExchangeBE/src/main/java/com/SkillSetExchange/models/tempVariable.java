package com.SkillSetExchange.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(	name = "temp_variable")
public class tempVariable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="chainPW")
	@Size(max = 20)
	public String chainPW;
	

	
}
