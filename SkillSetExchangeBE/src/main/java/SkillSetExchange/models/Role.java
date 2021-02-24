package SkillSetExchange.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	public ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}
}