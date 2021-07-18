package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Teacher DTO.
 * <BR>Provide all information related to {@link TeacherEntity}
 * @see TeacherEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value =  {"id", "username", "personality", "roleId", "version", "status"})
public class TeacherDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonProperty(value = "ID")
	private Integer id;	
	
	@JsonProperty(value = "Username")
	private String username;
	
	@JsonProperty(value = "Personality")
	private PersonDTO personality;	

	@JsonProperty(value = "Role")
	private RoleDTO role;	
	
	@JsonProperty(value = "Version")
	private Integer version;
	
	@JsonProperty(value = "Status")
	private EStatus status;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public TeacherDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getUsername() {
		return username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonDTO getPersonality() {
		return personality;
	}

	public void setPersonality(PersonDTO personality) {
		this.personality = personality;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
