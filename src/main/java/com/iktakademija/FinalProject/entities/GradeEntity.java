package com.iktakademija.FinalProject.entities;

import java.time.LocalDate;

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
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktakademija.FinalProject.entities.enums.EGradeType;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity
@Table(name = "grades")
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
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate entered;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private EStage stage;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idstd_grp")
	@JsonBackReference(value = "Student_Group_3")
	private JoinTableStudentGroup std_grp;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idsub_tch")
	@JsonBackReference(value = "Subject_Teacher_3")
	private JoinTableSubjectTeacher sub_tch;

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
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	public LocalDate getEntered() {
		return entered;
	}
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
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

	public JoinTableStudentGroup getStd_grp() {
		return std_grp;
	}

	public void setStd_grp(JoinTableStudentGroup std_grp) {
		this.std_grp = std_grp;
	}

	public JoinTableSubjectTeacher getSub_tch() {
		return sub_tch;
	}

	public void setSub_tch(JoinTableSubjectTeacher sub_tch) {
		this.sub_tch = sub_tch;
	}

}