package com.SkillSetExchange.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
@Entity
@Table(	name = "transaction_info")
public class TransactionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createDate = new Date();
	
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate = new Date();
	
	@Column(name="transaction_hash")
	@Size(max = 64)
	public String transactionHash;
	
	
	/**
	 * Transaction status
	 * @return 1 = 
	 * 2 = 
	 * 3 =
	 */
	@Column(name="status")
	@Size(max = 1)
	public String status;
}
