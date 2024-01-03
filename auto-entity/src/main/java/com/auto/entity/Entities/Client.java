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
public class Client extends User{
	
	@Column(length=4)
	private String titre; 	//  M. / Mme
	
	@Column(nullable=false)
	private String GSM;

	@OneToOne(mappedBy="client", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private Compte comptes;

	@Column
	private Boolean hasCompte = true ;


}