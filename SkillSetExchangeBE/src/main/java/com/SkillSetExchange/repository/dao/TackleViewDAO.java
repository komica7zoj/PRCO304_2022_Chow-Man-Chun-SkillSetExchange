package com.SkillSetExchange.repository.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
public interface  TackleViewDAO {

	public Long getid();

	public String getskillSetName();

	public String getskillSetDescript();
	

	public int getacademicLevel();
	
	
	public String getusername();

	public String getskillCategoryName();

 
	public Date getcreateDate();
	
 
	public Long getskillSetCategoryId();
 
	public Long getskillSetInfoId();
	
 
	public Long gettackleSkillSetInfoId();
	public String gettackleSkillSetName();
 
	public String gettackleName();
 
	public String gettackleDescript();
	
 
	public Long gettransactionInfoId();
	
 
	public String getstatus();
 
	public String getstatusType();
 
	public String gettag();
	


	



}
