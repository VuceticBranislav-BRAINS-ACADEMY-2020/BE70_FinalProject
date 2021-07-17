package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity(name = "student")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = {Views.Admin.class})
public class StudentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "classgroup")
	@JsonBackReference(value = "Student_Group_1")
	private GroupEntity classgroup;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "clazz")
	@JsonBackReference(value = "Student_Class_1")
	private JoinTableStudentClass clazz;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	@JsonBackReference(value = "Student_Parent_1")
	private JoinTableStudentParent parent;
	
	/************************************************************
	 * Shadow Attributes
	 ************************************************************/
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public StudentEntity() {
		super();
	}	
	
	public StudentEntity(String username, String password, PersonEntity personality, RoleEntity role) {
		super(username, password, personality, role);
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public GroupEntity getClassgroup() {
		return classgroup;
	}

	public void setClassgroup(GroupEntity classgroup) {
		this.classgroup = classgroup;
	}

	public JoinTableStudentClass getClazz() {
		return clazz;
	}

	public void setClazz(JoinTableStudentClass clazz) {
		this.clazz = clazz;
	}

	public JoinTableStudentParent getParent() {
		return parent;
	}

	public void setParent(JoinTableStudentParent parent) {
		this.parent = parent;
	}	

}