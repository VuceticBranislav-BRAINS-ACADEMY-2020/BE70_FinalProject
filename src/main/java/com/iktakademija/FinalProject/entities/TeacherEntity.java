package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "teacher")
//@Table(name = "teacher")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class TeacherEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToOne(mappedBy = "homeClassMaster", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private ClassEntity homeClass;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_teacher", joinColumns = {
			@JoinColumn(name = "idt", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ids", nullable = false, updatable = false) })
	private Set<SubjectEntity> subjects = new HashSet<>();

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Grade_Teacher_1")
	private Set<GradeEntity> grades = new HashSet<>();

	/************************************************************
	 * Constructors
	 ************************************************************/

	public TeacherEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public ClassEntity getHomeClass() {
		return homeClass;
	}

	public void setHomeClass(ClassEntity homeClass) {
		this.homeClass = homeClass;
	}

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

}
