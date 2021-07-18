package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Class_1")
	private Set<JoinTableStudentClass> clazz = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Parent_1")
	private Set<JoinTableStudentParent> parent = new HashSet<>();
	
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

	@JsonIgnore
	public Set<JoinTableStudentClass> getClazz() {
		return clazz;
	}
	
	@JsonIgnore
	public void setClazz(Set<JoinTableStudentClass> clazz) {
		this.clazz = clazz;
	}
	
	@JsonIgnore
	public Set<JoinTableStudentParent> getParent() {
		return parent;
	}

	@JsonIgnore
	public void setParent(Set<JoinTableStudentParent> parent) {
		this.parent = parent;
	}

}