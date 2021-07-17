package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Address DTO. <BR>
 * Provide all information needed for creating of new address.
 * 
 * @see AddressEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = { "city", "street", "number", "apartment" })
public class NewAddressDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@JsonProperty(value = "City")
	private String city;

	@JsonProperty(value = "Stresst")
	private String street;

	@JsonProperty(value = "Number")
	private String number;

	@JsonProperty(value = "Apartment")
	private String apartment;

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

}
