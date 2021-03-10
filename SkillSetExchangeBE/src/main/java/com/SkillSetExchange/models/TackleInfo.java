package com.SkillSetExchange.models;

import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;



@Data
@Entity
@Table(	name = "tackle_info")
public class TackleInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotBlank
	@Column(name="username")
	@Size(max = 20)
	public String username;
	

	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDate = new Date();
	
	@Column(name="skill_set_category_id")
	public Long skillSetCategoryId;
	
	@Column(name="tackle_name")
	@Size(max = 100)
	public String tackleName;
	
	@Column(name="tackle_descript")
	@Size(max = 5000)
	public String tackleDescript;
	
	@Column(name="transaction_info_id")
	public Long transactionInfoId;
	
	/**
	 * Tackle status
	 * @return 1 = \r\n
	 * 2 = \r\n
	 * 3 = \r\n
	 */
	@Column(name="status")
	@Size(max = 1)
	public String status;
	
	@Column(name="tag")
	@Size(max = 100)
	public String tag;
	

}
