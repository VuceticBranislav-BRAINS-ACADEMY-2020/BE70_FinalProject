package com.iktakademija.FinalProject.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "admin")
@Table(name = "admin")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@PrimaryKeyJoinColumn(name = "id")
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

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
}
