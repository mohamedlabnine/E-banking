package com.auto.entity.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private int amount ;
    private String secret ;
    private int commission ;
    private Date date ;
    private Date transferRetrait ;
    private boolean valid ;
    private String transferType ;
    private String ville ;
    @ManyToOne
    private Beneficiary beneficiary ;
    @ManyToOne
    private Sender sender ;
}
