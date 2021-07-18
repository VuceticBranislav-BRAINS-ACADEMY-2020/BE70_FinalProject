package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Student DTO.
 * <BR>Provide all information related to {@link StudentEntity}.
 * @see StudentEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value =  {"id", "username", "personality", "roleId", "version", "status", "classgroup"})
public class StudentDTO {
	
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
	
	@JsonProperty(value = "Class")
	private GroupDTO classgroup;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public StudentDTO() {
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

	public GroupDTO getClassgroup() {
		return classgroup;
	}

	public void setClassgroup(GroupDTO classgroup) {
		this.classgroup = classgroup;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
