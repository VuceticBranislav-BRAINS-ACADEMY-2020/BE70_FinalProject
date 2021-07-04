package com.iktakademija.FinalProject.entities;

import com.iktakademija.FinalProject.entities.enums.ERole;

public class UserEntity {
	
	private String password;
	private String username;
	private Boolean archived;
	private ERole role;

	public UserEntity() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

}
