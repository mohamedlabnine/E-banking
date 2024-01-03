package com.auto.entity.Shared.dto;

import java.io.Serializable;

public class ClientDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private String clientId;
    private String fullName;
    private String titre; 	//  M. / Mme
    private String GSM;
    private String numCompte;
    private String email;
    private Boolean hasCompte = true;

    public Boolean getHasCompte() {
        return hasCompte;
    }

    public void setHasCompte(Boolean hasCompte) {
        this.hasCompte = hasCompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }
}
