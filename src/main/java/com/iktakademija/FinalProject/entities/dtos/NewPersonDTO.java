package com.iktakademija.FinalProject.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Person DTO.
 * <BR>Provide all information needed for creating of new person.
 * @see PersonEntity
 * @author GM
 */
@JsonPropertyOrder(value = {"firstname", "lastname", "jmbg", "mphone", "birthdate", "address"})
@JsonView(value = Views.Admin.class)
public class NewPersonDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotBlank
	@JsonProperty(value = "First Name")
	private String firstname;
	
	@NotBlank
	@JsonProperty(value = "Last Name")
	private String lastname;
	
	@NotBlank
	@JsonProperty(value = "JMBG")
	private String jmbg;	
	
	@JsonProperty(value = "Phone Number")
	private String mphone;	

	@NotBlank
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonProperty(value = "Birth Date")
	private LocalDate birthdate;

	@NotBlank
	@JsonProperty(value = "ID Address")
	private Integer address;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewPersonDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
	
}
