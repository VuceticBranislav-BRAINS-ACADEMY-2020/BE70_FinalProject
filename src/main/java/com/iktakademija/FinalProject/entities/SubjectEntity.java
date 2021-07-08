package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "subject")
@Table(name = "subject")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class SubjectEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	private String name;
	private Integer fond;
	private String plan;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_teacher", joinColumns = {
			@JoinColumn(name = "idsubject", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idteacher", nullable = false, updatable = false) })
	private Set<TeacherEntity> teacher = new HashSet<>();
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Grade_Subject_1")
	private Set<GradeEntity> grades = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_class", joinColumns = {
			@JoinColumn(name = "idsubject", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idclass", nullable = false, updatable = false) })
	private Set<ClassEntity> classes = new HashSet<>();

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	private Integer deleted;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public SubjectEntity() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Set<TeacherEntity> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<TeacherEntity> teacher) {
		this.teacher = teacher;
	}

	public Set<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}

	public Set<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(Set<ClassEntity> classes) {
		this.classes = classes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}	

}
