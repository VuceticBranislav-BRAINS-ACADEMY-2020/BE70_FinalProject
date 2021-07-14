package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity(name = "classgroup")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class GroupEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@Column
	private String letter;		

	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "homeClassMaster")
	private TeacherEntity homeClassMaster;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "clazz")
	@JsonBackReference(value = "Group_Class_1")
	private ClassEntity clazz;
	
	@OneToMany(mappedBy = "classgroup", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Group_1")
	private Set<StudentEntity> students = new HashSet<>();
	
	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	@Column
	@Enumerated(value = EnumType.STRING)
	private EStatus status;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public GroupEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public TeacherEntity getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(TeacherEntity homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
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

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}
	
}