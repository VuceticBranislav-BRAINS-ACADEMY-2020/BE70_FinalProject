package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Address DTO. <BR>
 * Provide all information needed for creating or change new address.
 * 
 * @see AddressEntity
 * @author GM
 */
@JsonPropertyOrder(value = { "city", "street", "number", "apartment", "status" })
@JsonView(value = Views.Admin.class)
public class NewAddressDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@NotBlank
	@JsonProperty(value = "City")
	private String city;
	
	@NotBlank
	@JsonProperty(value = "Stresst")
	private String street;
	
	@NotBlank
	@JsonProperty(value = "Number")
	private String number;

	@JsonProperty(value = "Apartment")
	private String apartment;
	
	@JsonProperty(value = "Status")
	private EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewAddressDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

}
