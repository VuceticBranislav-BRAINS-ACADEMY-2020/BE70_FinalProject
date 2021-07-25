package com.iktakademija.FinalProject.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Person DTO.
 * <BR>Provide all information related to {@link PersonEntity}.
 * @see PersonEntity
 * @author GM
 */
@JsonView(value = Views.Student.class)
@JsonPropertyOrder(value =  {"id", "firstname", "lastname", "jmbg", "mphone", "birthdate", "address", "version", "status"})
public class PersonDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonProperty(value = "ID")
	private Integer id;		

	@JsonProperty(value = "First Name")
	private String firstname;
	
	@JsonProperty(value = "Last Name")
	private String lastname;
	
	@JsonProperty(value = "JMBG")
	private String jmbg;
	
	@JsonProperty(value = "Phone Number")
	private String mphone;
	
	@JsonProperty(value = "Birth Date")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate birthdate;

	@JsonProperty(value = "Address")
	private AddressDTO address;
	
	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Version")
	private Integer version;
	
	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Status")
	private EStatus status;		
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public PersonDTO() {
		super();
	}	

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}	

}
