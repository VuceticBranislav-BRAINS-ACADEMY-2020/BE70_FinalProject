package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "parent")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class ParentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column
	private String email;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "student")
	@JsonBackReference(value = "Student_Parent_2")
	private JoinTableStudentParent student;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public ParentEntity() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JoinTableStudentParent getStudent() {
		return student;
	}

	public void setStudent(JoinTableStudentParent student) {
		this.student = student;
	}

}