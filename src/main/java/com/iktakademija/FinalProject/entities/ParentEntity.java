package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity
@Table(name = "parents")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = { Views.Admin.class })
public class ParentEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	private String email;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Student_Parent_2")
	private Set<JoinTableStudentParent> student = new HashSet<>();

	/************************************************************
	 * Constructors
	 ************************************************************/

	public ParentEntity() {
		super();
	}

	public ParentEntity(String username, String password, PersonEntity person, RoleEntity role) {
		super(username, password, person, role);
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<JoinTableStudentParent> getStudents() {
		return student;
	}

	public void setStudent(Set<JoinTableStudentParent> student) {
		this.student = student;
	}

}