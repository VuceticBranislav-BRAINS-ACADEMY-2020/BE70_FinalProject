package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student_parents", uniqueConstraints = { @UniqueConstraint(columnNames = { "idstudent", "idparent" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableStudentParent {
	
	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idstudent", nullable = false)
	@JsonBackReference(value = "Student_Parent_1")
	private StudentEntity student;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idparent", nullable = false)
	@JsonBackReference(value = "Student_Parent_2")
	private ParentEntity parent;
	
	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableStudentParent() {
		super();
	}
	
	public JoinTableStudentParent(ParentEntity parent, StudentEntity student) {
		super();
		this.parent = parent;
		this.student = student;
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}