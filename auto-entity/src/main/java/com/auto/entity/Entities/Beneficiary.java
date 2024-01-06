package com.auto.entity.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(nullable=false,unique = true)
    private String beneficiaryId;
    private String fullName ;
    private String ville ;
    private String address ;
    private String CIN ;
    private String GSM;

}
