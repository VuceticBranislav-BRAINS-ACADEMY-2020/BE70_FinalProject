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
@Table(name = "subject_teachers", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "idteachers", "idgroup", "idsub_cls" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class JoinTableSubjectTeacher {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idteachers", nullable = false)
	@JsonBackReference(value = "Subject_Teacher_1")
	private TeacherEntity teachers;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idsub_cls", nullable = false)
	@JsonBackReference(value = "Subject_Teacher_2")
	private JoinTableSubjectClass sub_cls;

	@OneToMany(mappedBy = "sub_tch", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_3")
	private Set<GradeEntity> grade = new HashSet<>();

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idgroup", nullable = false)
	@JsonBackReference(value = "Student_Group_4")
	private GroupEntity group;

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableSubjectTeacher() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public TeacherEntity getTeachers() {
		return teachers;
	}

	public void setTeachers(TeacherEntity teachers) {
		this.teachers = teachers;
	}

	public JoinTableSubjectClass getSub_cls() {
		return sub_cls;
	}

	public void setSub_cls(JoinTableSubjectClass sub_cls) {
		this.sub_cls = sub_cls;
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