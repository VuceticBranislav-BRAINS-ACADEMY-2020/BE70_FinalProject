package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "student")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class StudentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/
	
	/************************************************************
	 * Relation Attributes
	 ************************************************************/
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "group")
	@JsonBackReference(value = "Student_Group_1")
	private GroupEntity group;
	
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
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
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