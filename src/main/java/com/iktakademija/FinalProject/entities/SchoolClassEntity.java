package com.iktakademija.FinalProject.entities;

public class SchoolClassEntity {

	private String name;
	private ClassEntity clazz;
	private TeacherEntity classMaster;
	private StudentEntity student;

	public SchoolClassEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

	public TeacherEntity getClassMaster() {
		return classMaster;
	}

	public void setClassMaster(TeacherEntity classMaster) {
		this.classMaster = classMaster;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

}
