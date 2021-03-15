package com.SkillSetExchange.repository.dao;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
public interface  SkillSetSearchResultDAO {

	public Long getid();

	public String getskillSetName();
	

	public String getskillSetDescript();
	

	public int getacademicLevel();
	

	public String gettag();
	
	
	public String getusername();

	public String getskillCategoryName();


	


	



}
