package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Group DTO.
 * <BR>Provide all information related to {@link GroupEntity}.
 * @see GroupEntity
 * @author GM
 */
@JsonView(value = Views.Student.class)
@JsonPropertyOrder(value =  {"letter", "homeClassMaster", "clazz", "status"})
public class NewGroupDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@NotEmpty(message = "Class mark can not be empty.")
	@JsonProperty(value = "Class Mark")
	private String letter;	

	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Homeclass Teacher")
	private Integer homeClassMaster;
	
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Class")
	private Integer clazz;
	
	@JsonProperty(value = "Status")
	private EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewGroupDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Integer getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(Integer homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

}
