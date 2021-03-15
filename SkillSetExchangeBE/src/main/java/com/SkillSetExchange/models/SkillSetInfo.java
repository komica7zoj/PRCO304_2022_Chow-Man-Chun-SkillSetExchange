package com.SkillSetExchange.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(	name = "skill_set_info")
public class SkillSetInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotBlank
	@Size(max = 20)
	public String username;
	
	@Column(name="skill_set_category_id")
	public Long skillCategoryId;
	
	@Column(name="skill_set_name")
	@Size(max = 100)
	public String SkillSetName;
	
	@Column(name="skill_set_descript")
	@Size(max = 5000)
	public String skillSetDescript;
	
	@Column(name="academic_level")
	public int academicLevel = 1;
	
	@Column(name="tag")
	@Size(max = 100)
	public String tag;
	
}
