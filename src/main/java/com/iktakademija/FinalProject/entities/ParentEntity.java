package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "parent")
@Table(name = "parent")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class ParentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "student_parent", joinColumns = {
			@JoinColumn(name = "idparent", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idstudent", nullable = false, updatable = false) })
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

}
