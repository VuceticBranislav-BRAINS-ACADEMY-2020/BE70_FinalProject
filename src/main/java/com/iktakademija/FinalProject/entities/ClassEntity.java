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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

@Entity
@Table(name = "classes", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "year" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@JsonView(value = Views.Student.class)
public class ClassEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer year;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToMany(mappedBy = "clazz", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Class_1")
	private Set<JoinTableSubjectClass> subject = new HashSet<>();

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	@JsonView(value = Views.Admin.class)
	private Integer version;

	@Column
	@Enumerated(value = EnumType.STRING)
	private EStatus status;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Set<JoinTableSubjectClass> getSubject() {
		return subject;
	}

	public void setSubject(Set<JoinTableSubjectClass> subject) {
		this.subject = subject;
	}

}