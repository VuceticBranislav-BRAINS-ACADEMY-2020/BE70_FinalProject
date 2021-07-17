package com.iktakademija.FinalProject.entities;

import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktakademija.FinalProject.entities.enums.EGradeType;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity(name = "grade")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class GradeEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private EGradeType type;
	
	@Column(nullable = false)
	private Integer value;
	
	@Column
	private LocalDate entered;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private EStage stage;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@OneToMany(mappedBy = "grade", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Class_3")
	private Set<JoinTableSubjectClass> sub_cls = new HashSet<>();
	
	@OneToMany(mappedBy = "grade", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_3")
	private Set<JoinTableStudentClass> std_cls = new HashSet<>();
	
	@OneToMany(mappedBy = "grade", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_3")
	private Set<JoinTableSubjectTeacher> sub_tch = new HashSet<>();

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

	public GradeEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public EGradeType getType() {
		return type;
	}

	public void setType(EGradeType type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public LocalDate getEntered() {
		return entered;
	}

	public void setEntered(LocalDate entered) {
		this.entered = entered;
	}

	public EStage getStage() {
		return stage;
	}

	public void setStage(EStage stage) {
		this.stage = stage;
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

	public Set<JoinTableStudentClass> getStd_cls() {
		return std_cls;
	}

	public void setStd_cls(Set<JoinTableStudentClass> std_cls) {
		this.std_cls = std_cls;
	}

	public Set<JoinTableSubjectClass> getSub_cls() {
		return sub_cls;
	}

	public void setSub_cls(Set<JoinTableSubjectClass> sub_cls) {
		this.sub_cls = sub_cls;
	}

	public Set<JoinTableSubjectTeacher> getSub_tch() {
		return sub_tch;
	}

	public void setSub_tch(Set<JoinTableSubjectTeacher> sub_tch) {
		this.sub_tch = sub_tch;
	}

}