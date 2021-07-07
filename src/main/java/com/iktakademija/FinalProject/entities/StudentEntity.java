package com.iktakademija.FinalProject.entities;

import java.util.List;

public class StudentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	private ClassEntity clazz;
	private List<ParentEntity> parents;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public StudentEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

	public List<ParentEntity> getParents() {
		return parents;
	}

	public void setParents(List<ParentEntity> parents) {
		this.parents = parents;
	}

}
