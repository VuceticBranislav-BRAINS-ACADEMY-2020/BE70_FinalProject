package com.iktakademija.FinalProject.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.enums.EGradeType;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Grade DTO. <BR>
 * Provide all information needed for adding new grade.
 * 
 * @see GradeEntity
 * @author GM
 */
@JsonPropertyOrder(value = { "type", "value", "stage", "idStudent", "idTeacher", "idSubject", "idGroup"})
@JsonView(value = Views.Teacher.class)
public class NewGradeDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotNull(message = "Grade type must be: EXAM, WRITTEN, ORAL or FINAL.")
	@JsonProperty(value = "Type")
	private EGradeType type;
	
	@NotNull(message = "Pleas enter grade value foomr 1 to 5.")
	@Min(value = 1, message = "Grade must be at least 1.")
	@Max(value = 5, message = "Grade can be 5 or less.")
	@JsonProperty(value = "Value")
	private Integer value;
	
	@NotNull(message = "Stage must be FIRST or SECOND.")
	@JsonProperty(value = "Stage")
	private EStage stage;	

	@NotNull(message = "Must not be null.")
	@Positive(message = "Student with given ID do not exists. Pleas enter valid student ID.")
	@JsonProperty(value = "ID Student")
	private Integer idStudent;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Teacher with given ID do not exists. Pleas enter valid teacher ID.")
	@JsonProperty(value = "ID Teacher")
	private Integer idTeacher;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Subject with given ID do not exists. Pleas enter valid subject ID.")
	@JsonProperty(value = "ID Subject")
	private Integer idSubject;	
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Group with given ID do not exists. Pleas enter valid group ID.")
	@JsonProperty(value = "ID Group")
	private Integer idGroup;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public NewGradeDTO() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/

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

	public EStage getStage() {
		return stage;
	}

	public void setStage(EStage stage) {
		this.stage = stage;
	}

	public Integer getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Integer idStudent) {
		this.idStudent = idStudent;
	}

	public Integer getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Integer idTeacher) {
		this.idTeacher = idTeacher;
	}

	public Integer getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Integer idSubject) {
		this.idSubject = idSubject;
	}
	
	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	@Override
	public String toString() {
		return "NewGradeDTO [type=" + type + ", value=" + value + ", stage=" + stage + ", idStudent=" + idStudent
				+ ", idTeacher=" + idTeacher + ", idSubject=" + idSubject + "]";
	}
	
}
