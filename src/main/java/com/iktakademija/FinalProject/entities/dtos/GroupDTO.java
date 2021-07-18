package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Group DTO.
 * <BR>Provide all information related to {@link GroupEntity}.
 * @see GroupEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value =  {"id", "letter", "homeClassMaster", "clazz", "version", "status"})
public class GroupDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonProperty(value = "ID")
	private Integer id;	
	
	@JsonProperty(value = "Class Mark")
	private String letter;	

	@JsonProperty(value = "Homeclass Teacher")
	private TeacherEntity homeClassMaster;
	
	@JsonProperty(value = "Class")
	private ClassEntity clazz;
	
	@JsonProperty(value = "Version")
	private Integer version;
	
	@JsonProperty(value = "Status")
	private EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public GroupDTO() {
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

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public TeacherEntity getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(TeacherEntity homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
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
