package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "student_class")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableStudentClass {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToMany(mappedBy = "clazz", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_1")
	private Set<StudentEntity> student = new HashSet<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_2")
	private Set<ClassEntity> clazz = new HashSet<>();
	
	@OneToMany(mappedBy = "std_cls", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_3")
	private Set<GradeEntity> grade = new HashSet<>();
	
	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableStudentClass() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public Set<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(Set<StudentEntity> student) {
		this.student = student;
	}

	public Set<ClassEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Set<ClassEntity> clazz) {
		this.clazz = clazz;
	}

	public Set<GradeEntity> getGrade() {
		return grade;
	}

	public void setGrade(Set<GradeEntity> grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

}