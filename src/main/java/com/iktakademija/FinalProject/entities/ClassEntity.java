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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "clazz")
@Table(name = "clazz")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class ClassEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	private String name;
	
	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@OneToOne(cascade =CascadeType.REFRESH,fetch =FetchType.LAZY)
	@JoinColumn(name ="homeclass")
	private TeacherEntity homeClassMaster;
	
	@OneToMany(mappedBy = "clazz", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_1")
	private Set<StudentEntity> students = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_class", joinColumns = {
			@JoinColumn(name = "idclass", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idsubject", nullable = false, updatable = false) })
	private Set<SubjectEntity> subjects = new HashSet<>();

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

	public ClassEntity() {
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

	public TeacherEntity getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(TeacherEntity homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
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

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

}
