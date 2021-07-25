package com.iktakademija.FinalProject.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.enums.EGradeType;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Class DTO.
 * <BR>Provide all information related to {@link ClassEntity}.
 * @see ClassEntity
 * @author GM
 */
@JsonView(value = Views.Student.class)
@JsonPropertyOrder(value =  {"id", "type", "value", "entered", "stage", "version", "status", "groupName", "teacherName", "subjectName"})
public class GradeDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonView(value = Views.Teacher.class)
	@JsonProperty(value = "ID")
	private Integer id;	
	
	@JsonProperty(value = "Type")
	private EGradeType type;
	
	@JsonProperty(value = "Value")
	private Integer value;
	
	@JsonProperty(value = "Date Entered")
	private LocalDate entered;
	
	@JsonProperty(value = "Stage")
	private EStage stage;	
	
	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Version")
	private Integer version;
	
	@JsonProperty(value = "Status")
	private EStatus status;	
	
	@JsonProperty(value = "Group")
	private String groupName;

	@JsonProperty(value = "Teacher")
	private String teacherName;
	
	@JsonProperty(value = "Subject")
	private String subjectName;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public GradeDTO() {
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

	public EGradeType getType() {
		return type;
	}

	public void setType(EGradeType type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public LocalDate getEntered() {
		return entered;
	}

	public void setEntered(LocalDate entered) {
		this.entered = entered;
	}

	public EStage getStage() {
		return stage;
	}

	public void setStage(EStage stage) {
		this.stage = stage;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
