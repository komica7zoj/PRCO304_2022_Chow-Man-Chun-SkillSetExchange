package com.SkillSetExchange.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(	name = "user_multichain_content")
public class UserMultichainContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotBlank
	@Column(name="username")
	@Size(max = 20)
	public String username;
	
	@Column(name="multichain_address")
	@Size(max = 38)
	public String multichainAddress;
	
	@Column(name="rpc_password")
	@Size(max = 44)
	public String rpcPassword;
	
	@Column(name="connection_port")
	public int connectionPort;
}
