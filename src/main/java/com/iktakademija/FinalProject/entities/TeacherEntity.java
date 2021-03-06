package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity
@Table(name = "teachers")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = { Views.Admin.class })
public class TeacherEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToOne(mappedBy = "homeClassMaster", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private GroupEntity homeClassMaster;

//	@JsonIgnore
	@OneToMany(mappedBy = "teachers", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Subject_Teacher_1")
	private Set<JoinTableSubjectTeacher> subject = new HashSet<>();

	/************************************************************
	 * Constructors
	 ************************************************************/

	public TeacherEntity() {
		super();
	}

	public TeacherEntity(String username, String password, PersonEntity person, RoleEntity role) {
		super(username, password, person, role);
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public GroupEntity getHomeClassMaster() {
		return homeClassMaster;
	}

	public void setHomeClassMaster(GroupEntity homeClassMaster) {
		this.homeClassMaster = homeClassMaster;
	}

//	@JsonIgnore
	public Set<JoinTableSubjectTeacher> getSubject() {
		return subject;
	}

//	@JsonIgnore
	public void setSubject(Set<JoinTableSubjectTeacher> subject) {
		this.subject = subject;
	}

}