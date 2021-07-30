package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.securities.Views;

/**
 * JoinTableStudentGroup DTO. <BR>
 * Provide all information related to {@link JoinTableStudentGroup}.
 * 
 * @see JoinTableStudentGroup
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = { "id", "student", "group" })
public class JoinTableStudentGroupDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotNull(message = "Must not be null.")
	@JsonProperty(value = "ID")
	private Integer id;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Student")
	private Integer student;
	
	@NotNull(message = "Must not be null.")
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Group")
	private Integer group;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableStudentGroupDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public Integer getStudent() {
		return student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

}
