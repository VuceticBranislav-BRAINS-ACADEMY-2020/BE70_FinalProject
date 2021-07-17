package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Administration DTO.
 * <BR>Provide all information needed for creating of new user.
 * @see AdminEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = {"username", "password", "personalityId", "role"})
public class NewUserDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonProperty(value = "Username")
	private String username;

	@JsonProperty(value = "Password")
	private String password;

	@JsonProperty(value = "ID Personality")
	private Integer personalityId;	

	@JsonProperty(value = "ID Role")
	private ERole role;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewUserDTO() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/

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

	public Integer getPersonalityId() {
		return personalityId;
	}

	public void setPersonalityId(Integer personalityId) {
		this.personalityId = personalityId;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

}
