package com.auto.entity.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client{
	

	@GeneratedValue
	private long id;
	@Id
	@Column(nullable=false)
	private String clientId;
	
	@Column(nullable=false, length=100)
	private String fullName;
	
	@Column(length=4)
	private String titre; 	//  M. / Mme
	
	@Column(nullable=false)
	private String GSM;

	@Column(nullable=false)
	private String email;

	@OneToOne(mappedBy="client", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private Compte comptes;

	@Column
	private Boolean hasCompte = true ;


}