package com.iktakademija.FinalProject.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Group DTO. <BR>
 * Provide all information related to {@link GroupEntity}.
 * 
 * @see GroupEntity
 * @author GM
 */
@JsonView(value = Views.Student.class)
@JsonPropertyOrder(value = { "id", "letter", "homeClassMaster", "clazz", "version", "status" })
public class GroupDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@JsonView(value = Views.Teacher.class)
	@JsonProperty(value = "ID")
	private Integer id;

	@JsonProperty(value = "Class Mark")
	private String letter;

	@JsonProperty(value = "Homeclass Teacher")
	private TeacherDTO homeClassMaster;

	@JsonProperty(value = "Class")
	private ClassDTO clazz;

	@JsonView(value = Views.Admin.class)
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

	public ClassDTO getClazz() {
		return clazz;
	}

	public void setClazz(ClassDTO clazz) {
		this.clazz = clazz;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public TeacherDTO getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(TeacherDTO homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

}
