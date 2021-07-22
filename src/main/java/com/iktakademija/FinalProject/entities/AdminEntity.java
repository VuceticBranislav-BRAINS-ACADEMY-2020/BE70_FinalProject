package com.iktakademija.FinalProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity
@Table(name = "admins")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = {Views.Admin.class})
public class AdminEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column
	private String email;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public AdminEntity() {
		super();
	}

	public AdminEntity(String username, String password, PersonEntity person, RoleEntity role) {
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
	
}
