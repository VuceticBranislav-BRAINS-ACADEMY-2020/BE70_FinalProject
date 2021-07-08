package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "student")
@Table(name = "student")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class StudentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Grade_Student_1")
	private Set<GradeEntity> grades = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "clazz")
	@JsonBackReference(value = "Student_Class_1")
	private ClassEntity clazz;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "student_parent", joinColumns = {
			@JoinColumn(name = "idstudent", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idparent", nullable = false, updatable = false) })
	@JsonManagedReference(value = "Student_Parent_1")
	private Set<ParentEntity> parents = new HashSet<>();

	/************************************************************
	 * Constructors
	 ************************************************************/

	public StudentEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

	public Set<ParentEntity> getParents() {
		return parents;
	}

	public void setParents(Set<ParentEntity> parents) {
		this.parents = parents;
	}

}
