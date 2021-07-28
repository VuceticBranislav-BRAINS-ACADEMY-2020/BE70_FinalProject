package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.securities.Views;
import com.sun.istack.NotNull;

/**
 * Administration DTO.
 * <BR>Provide all information needed for creating of new user.
 * @see AdminEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = {"username", "password", "person"})
public class NewStudentDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@NotBlank(message = "Can not be blank or null.")
	@JsonProperty(value = "Username")
	private String username;

	@NotBlank(message = "Can not be blank or null.")
	@JsonProperty(value = "Password")
	private String password;

	@NotNull
	@JsonProperty(value = "ID Person")
	private Integer personId;	
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewStudentDTO() {
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

}
