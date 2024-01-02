package com.auto.entity.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="agents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@ManyToOne
	@JoinColumn(name="agenceId")
	private Agence agence;

}
