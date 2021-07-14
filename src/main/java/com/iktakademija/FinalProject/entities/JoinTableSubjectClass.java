package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "subject_class")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableSubjectClass {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Class_1")
	private Set<ClassEntity> clazz = new HashSet<>();

	@OneToMany(mappedBy = "clazz", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Class_2")
	private Set<SubjectEntity> subject = new HashSet<>();
	
	@OneToMany(mappedBy = "sub_cls", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Class_3")
	private Set<GradeEntity> grade = new HashSet<>();

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableSubjectClass() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public Set<ClassEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Set<ClassEntity> clazz) {
		this.clazz = clazz;
	}

	public Set<SubjectEntity> getSubject() {
		return subject;
	}

	public void setSubject(Set<SubjectEntity> subject) {
		this.subject = subject;
	}

	public Set<GradeEntity> getGrade() {
		return grade;
	}

	public void setGrade(Set<GradeEntity> grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

}