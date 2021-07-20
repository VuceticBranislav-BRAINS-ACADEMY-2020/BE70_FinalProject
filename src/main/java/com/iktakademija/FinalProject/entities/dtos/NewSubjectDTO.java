package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Subject DTO.
 * <BR>Provide all information needed for creating of new subject.
 * @see SubjectEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = {"name", "fond"})
public class NewSubjectDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@NotBlank
	@JsonProperty(value = "Name")
	private String name;

	@NotBlank
	@JsonProperty(value = "Fond")
	private Integer fond;	
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewSubjectDTO() {
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

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

}
