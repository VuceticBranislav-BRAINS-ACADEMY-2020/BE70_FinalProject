package com.iktakademija.FinalProject.entities;

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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity(name = "subject")
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "clazz")
	@JsonBackReference(value = "Subject_Class_2")
	private JoinTableSubjectClass clazz;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher")
	@JsonBackReference(value = "Subject_Teacher_2")
	private JoinTableSubjectTeacher teacher;
	
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

	public JoinTableSubjectClass getClazz() {
		return clazz;
	}

	public void setClazz(JoinTableSubjectClass clazz) {
		this.clazz = clazz;
	}

	public JoinTableSubjectTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(JoinTableSubjectTeacher teacher) {
		this.teacher = teacher;
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
