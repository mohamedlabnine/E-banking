package com.auto.entity.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="transferts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfert {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false)
	private String transfertId;

	@Column(length=13)
	private String referenceTransfert;
	
	@Column(length=4)
	private String pin;
	
	@Column(nullable=false)
	private String etat = "A servir";

	@Column(nullable=false)
	private float montant;
	
	@Column
	private String motif;
	
	@Column
	private Boolean notification = false;
	
	@Column
	private Boolean GAB_BOA = false;

	@ManyToOne
	@JoinColumn(name="clientDonneurId")
	private Client clientDonneur;

	@ManyToOne
	@JoinColumn(name="clientBeneficaireId")
	private Client clientBeneficaire;

	Date now = new Date(System.currentTimeMillis());

	@Column(nullable=false)
	private Date dateTransfert = now;

	@Column
	private Date dateReception;
	
	@Column
	private int delaiTransfert = 7;

	@Column(nullable=false)
	private String typeTransfert;


}
