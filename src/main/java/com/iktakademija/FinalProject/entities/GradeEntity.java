package com.iktakademija.FinalProject.entities;

import java.time.LocalDate;

import com.iktakademija.FinalProject.entities.enums.EGrade;
import com.iktakademija.FinalProject.entities.enums.EStage;

public class GradeEntity {

	private EGrade type;
	private Integer value;
	private LocalDate entred;
	private Integer generation;
	private EStage stage;
	private SubjectEntity subject;
	private StudentEntity student;
	private TeacherEntity teacher;

	public GradeEntity() {
		super();
	}

	public EGrade getType() {
		return type;
	}

	public void setType(EGrade type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public LocalDate getEntred() {
		return entred;
	}

	public void setEntred(LocalDate entred) {
		this.entred = entred;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public EStage getStage() {
		return stage;
	}

	public void setStage(EStage stage) {
		this.stage = stage;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

}
