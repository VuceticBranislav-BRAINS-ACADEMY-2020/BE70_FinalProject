package com.iktakademija.FinalProject.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.securities.Views;

/**
 * JoinTableSubjectTeacher DTO. <BR>
 * Provide all information related to {@link JoinTableSubjectTeacher}.
 * 
 * @see JoinTableSubjectTeacher
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = { "id", "group", "teacher", "sub_cls" })
public class JoinTableSubjectTeacherDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotNull(message = "Must not be null.")
	@JsonProperty(value = "ID")
	private Integer id;

	@NotNull(message = "Must not be null.")
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Group")	
	private Integer group;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Teacher")
	private Integer teacher;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Subject Class")
	private Integer sub_cls;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableSubjectTeacherDTO() {
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

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getTeacher() {
		return teacher;
	}

	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}

	public Integer getSub_cls() {
		return sub_cls;
	}

	public void setSub_cls(Integer sub_cls) {
		this.sub_cls = sub_cls;
	}

}
