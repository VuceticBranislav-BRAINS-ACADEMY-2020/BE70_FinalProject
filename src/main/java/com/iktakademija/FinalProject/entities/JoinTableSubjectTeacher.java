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

@Entity(name = "subject_teacher")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableSubjectTeacher {
	
	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_1")
	private Set<TeacherEntity> teachers = new HashSet<>();
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_2")
	private Set<SubjectEntity> subjects = new HashSet<>();
	
	@OneToMany(mappedBy = "sub_tch", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_3")
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

	public JoinTableSubjectTeacher() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public Set<TeacherEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeacherEntity> teachers) {
		this.teachers = teachers;
	}

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
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