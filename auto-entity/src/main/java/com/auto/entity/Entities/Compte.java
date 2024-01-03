package com.auto.entity.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="comptes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compte {

	@GeneratedValue
	private long id;
	@Id
	@Column(nullable=false)
	private String compteId;

	@Column(nullable=false, unique=true)
	private String numCompte;
	
	@Column(nullable=false)
	private float solde;
	
	@OneToOne
	@JoinColumn(name="clientId")
	private Client client;

	Date now = new Date(System.currentTimeMillis());

	@Column(nullable=false)
	private Date dateCreation = now;

}
