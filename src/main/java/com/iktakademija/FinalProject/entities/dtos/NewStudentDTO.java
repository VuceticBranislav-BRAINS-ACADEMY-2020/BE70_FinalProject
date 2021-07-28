package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.securities.Views;

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
	
	@Size(min = 5, max = 10, message = "Username must be between {min} and {max} characters long.")
	@JsonProperty(value = "Username")
	private String username;

	@Pattern(regexp = "^[A-Za-z\\d]{4,}$", message = "Password  must contain at least 4 characters.")
	@JsonProperty(value = "Password")
	private String password;

	@Positive(message = "Must not be positiv index number.")
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
