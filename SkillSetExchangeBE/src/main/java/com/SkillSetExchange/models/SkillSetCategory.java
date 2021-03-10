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
@Table(	name = "skill_set_category")
public class SkillSetCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="skill_set_type_id")
	public Long skillTypeId;
	
	@Column(name="category_name")
	@Size(max = 20)
	public String categoryName;
}
