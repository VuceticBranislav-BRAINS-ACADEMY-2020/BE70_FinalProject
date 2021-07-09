package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "parent")
//@Table(name = "parent")
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "student_parent", joinColumns = {
			@JoinColumn(name = "idp", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ids", nullable = false, updatable = false) })
	@JsonManagedReference(value = "Student_Parent_1")
	private Set<StudentEntity> students = new HashSet<>();

	/************************************************************
	 * Constructors
	 ************************************************************/

	public ParentEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
