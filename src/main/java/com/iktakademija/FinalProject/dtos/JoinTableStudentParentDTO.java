package com.iktakademija.FinalProject.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.securities.Views;

/**
 * JoinTableStudentParent DTO. <BR>
 * Provide all information related to {@link JoinTableStudentParent}.
 * 
 * @see JoinTableStudentParent
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = {"id", "student", "parent" })
public class JoinTableStudentParentDTO {
	
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
	@JsonProperty(value = "ID Parent")
	private Integer parent;	
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableStudentParentDTO() {
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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
}
