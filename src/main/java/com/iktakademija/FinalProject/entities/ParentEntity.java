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
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity(name = "parent")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = {Views.Admin.class})
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
	
	
	public ParentEntity(String username, String password, PersonEntity personality, RoleEntity role) {
		super(username, password, personality, role);
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