package com.iktakademija.FinalProject.entities;

import java.util.List;

public class TeacherEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	private ClassEntity homeClass;
	private List<StudentEntity> subjects;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public TeacherEntity() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public ClassEntity getHomeClass() {
		return homeClass;
	}

	public void setHomeClass(ClassEntity homeClass) {
		this.homeClass = homeClass;
	}

	public List<StudentEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<StudentEntity> subjects) {
		this.subjects = subjects;
	}

}
