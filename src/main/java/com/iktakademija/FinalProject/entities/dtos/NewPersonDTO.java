package com.iktakademija.FinalProject.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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

	@NotBlank(message = "Can not be blank or null.")
	@JsonProperty(value = "First Name")
	private String firstname;
	
	@NotBlank(message = "Can not be blank or null.")
	@JsonProperty(value = "Last Name")
	private String lastname;
	
	@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$", message = "Provide a valid JMBG.")
	@JsonProperty(value = "JMBG")
	private String jmbg;	
	
	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.\\/]\\d{3}[\\s.-]\\d{3}$", message = "Provide a valid phone number in format xxx/xxx-xxx.")
	@JsonProperty(value = "Phone Number")
	private String mphone;	

	@Past(message = "Can not be date in future.")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonProperty(value = "Birth Date")
	private LocalDate birthdate;

	@Positive(message = "Must be positiv index number.")
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
