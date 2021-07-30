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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "student_groups", uniqueConstraints = { @UniqueConstraint(columnNames = { "idstudent", "idgroup" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableStudentGroup {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idstudent", nullable = false)
	@JsonBackReference(value = "Student_Group_1")
	private StudentEntity student;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idgroup", nullable = false)
	@JsonBackReference(value = "Student_Group_2")
	private GroupEntity group;

	@OneToMany(mappedBy = "std_grp", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Group_3")
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

	public JoinTableStudentGroup() {
		super();
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

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
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