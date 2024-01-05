package com.auto.entity.Shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfertDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private String transfertId;
    private String referenceTransfert;
    private String pin;
    private String etat = "A servir";
    private float montant;
    private String motif;
    private Boolean notification = false;
    private Boolean GAB_BOA = false;
    private String clientDonneurId;
    private String clientBeneficaireId;
    private String agentId;
    private Date dateTransfert;
    private Date dateReception;
    private int delaiTransfert = 7;
    private String typeTransfert;
}
