package com.iktakademija.FinalProject.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@Entity(name = "admin")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
@JsonView(value = {Views.Admin.class})
public class AdminEntity extends UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	/************************************************************
	 * Constructors
	 ************************************************************/

	public AdminEntity() {
		super();
	}

	public AdminEntity(String username, String password, PersonEntity personality, RoleEntity role) {
		super(username, password, personality, role);
	}	
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
}
