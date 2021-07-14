package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "teacher")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
public class TeacherEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToOne(mappedBy = "homeClassMaster", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private GroupEntity homeClassMaster;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject")
	@JsonBackReference(value = "Subject_Teacher_1")
	private JoinTableSubjectTeacher subject;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public TeacherEntity() {
		super();
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

	public JoinTableSubjectTeacher getSubject() {
		return subject;
	}

	public void setSubject(JoinTableSubjectTeacher subject) {
		this.subject = subject;
	}	

}