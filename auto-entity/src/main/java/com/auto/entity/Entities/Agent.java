package com.auto.entity.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="agents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent extends User {

	@GeneratedValue
	private long id;
	@Id
	@Column(nullable=false)
	private String agentId;
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String encryptedPassword;
	@ManyToOne
	@JoinColumn(name="agenceId")
	private Agence agence;
	@OneToOne(mappedBy="agent", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private Compte compte;

}
