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
public class Agent extends User {

	@ManyToOne
	@JoinColumn(name="agenceId")
	private Agence agence;

}
