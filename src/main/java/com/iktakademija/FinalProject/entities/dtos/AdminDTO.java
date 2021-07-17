package com.iktakademija.FinalProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Administration DTO.
 * <BR>Provide all information related to {@link AdminEntity}. Relation are represented trough Id.
 * @see AdminEntity
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value =  {"id", "username", "personalityId", "roleId"})
public class AdminDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonProperty(value = "ID")
	private Integer id;	
	
	@JsonProperty(value = "Username")
	private String username;

	@JsonProperty(value = "ID Personality")
	private Integer personalityId;	

	@JsonProperty(value = "ID Role")
	private Integer roleId;	

	@JsonProperty(value = "Status")
	private EStatus status;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public AdminDTO() {
		super();
	}	
	
	public AdminDTO(AdminEntity source) {
		super();
		
		if (source.getId() != null) {
			this.setId(source.getId());	
		}
		
		if (source.getUsername() != null) {
			this.setUsername(source.getUsername());	
		}
		
		if (source.getPersonality() != null) {
			this.setPersonalityId(source.getPersonality().getId());	
		}
		
		if (source.getRole() != null) {
			this.setRoleId(source.getRole().getId());	
		}
		
		if (source.getStatus() != null) {
			this.setStatus(source.getStatus());	
		}
		
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPersonalityId() {
		return personalityId;
	}

	public void setPersonalityId(Integer personalityId) {
		this.personalityId = personalityId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

}
