package com.iktakademija.FinalProject.entities;

import java.util.List;

public class ParentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	private List<StudentEntity> students;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public ParentEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

}
