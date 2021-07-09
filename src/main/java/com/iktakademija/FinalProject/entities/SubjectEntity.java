package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity(name = "subject")
//@Table(name = "subject")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class SubjectEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	private String name;

	@Column
	private Integer fond;

	@Column
	private String plan;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_teacher", joinColumns = {
			@JoinColumn(name = "ids", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idt", nullable = false, updatable = false) })
	private Set<TeacherEntity> teacher = new HashSet<>();

	@OneToMany(mappedBy = "subject", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Grade_Subject_1")
	private Set<GradeEntity> grades = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_class", joinColumns = {
			@JoinColumn(name = "ids", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idc", nullable = false, updatable = false) })
	private Set<ClassEntity> classes = new HashSet<>();

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	@Column
	private EStatus status;

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

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

}
