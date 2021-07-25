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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "subject_classes", uniqueConstraints = { @UniqueConstraint(columnNames = { "idclass", "idsubject"}) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableSubjectClass {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column
	private Integer fond;
	
	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idclass", nullable = false)
	@JsonBackReference(value = "Subject_Class_1")
	private ClassEntity clazz;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idsubject", nullable = false)
	@JsonBackReference(value = "Subject_Class_2")
	private SubjectEntity subject;
	
	@OneToMany(mappedBy = "sub_cls", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_2")
	private Set<JoinTableSubjectTeacher> sub_cls = new HashSet<>();
	
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
	
	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public Set<JoinTableSubjectTeacher> getSub_cls() {
		return sub_cls;
	}

	public void setSub_cls(Set<JoinTableSubjectTeacher> sub_cls) {
		this.sub_cls = sub_cls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}