package com.auto.entity.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name="agences")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agence {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String adresseAgence;
	
	@Column(nullable=false)
	private String villeAgence;
	
	@Column(nullable=false, length=50)
    private String nomAgence;

	
	@Column(nullable=false)
	private String telephoneAgence;

	@Column(nullable=false)
    private boolean active = true;
	
	
	@OneToMany(mappedBy="agence", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Agent> agent;

}
