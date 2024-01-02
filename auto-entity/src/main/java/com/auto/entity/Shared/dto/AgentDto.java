package com.auto.entity.Shared.dto;

import java.io.Serializable;

public class AgentDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private String username;
    private String password;
    private Long agenceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }
}
