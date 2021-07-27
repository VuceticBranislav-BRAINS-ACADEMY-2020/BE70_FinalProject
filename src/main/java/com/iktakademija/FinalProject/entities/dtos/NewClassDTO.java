package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Class DTO.
 * <BR>Provide all information needed for creating of new class.
 * @see ClassEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = {"name", "year", "status"})
public class NewClassDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotBlank
	@JsonProperty(value = "Name")
	private String name;
	
	@NotBlank
	@JsonProperty(value = "Year")
	private Integer year;
	
	@JsonProperty(value = "Status")
	private EStatus status;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewClassDTO() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}
	
}
