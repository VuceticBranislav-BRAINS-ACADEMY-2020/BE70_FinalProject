package com.iktakademija.FinalProject.entities;

public class ClassEntity {

	private String name;
	private Integer generation;
	private Integer classGrade;

	public ClassEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public Integer getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(Integer classGrade) {
		this.classGrade = classGrade;
	}

}
