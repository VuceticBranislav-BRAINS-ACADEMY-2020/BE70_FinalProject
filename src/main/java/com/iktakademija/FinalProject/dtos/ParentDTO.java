package com.iktakademija.FinalProject.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Parent DTO. <BR>
 * Provide all information related to {@link ParentEntity}
 * 
 * @see ParentEntity
 * @author GM
 */
@JsonView(value = Views.Parent.class)
@JsonPropertyOrder(value = { "id", "username", "person", "roleId", "version", "status", "email", "childs" })
public class ParentDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@JsonProperty(value = "ID")
	private Integer id;

	@JsonProperty(value = "Username")
	private String username;

	@JsonProperty(value = "Person")
	private PersonDTO person;

	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Role")
	private RoleDTO role;

	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Version")
	private Integer version;

	@JsonProperty(value = "Status")
	private EStatus status;

	@JsonProperty(value = "eMail")
	private String email;

	@JsonProperty(value = "ID Childs")
	private List<StudentDTO> childs;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public ParentDTO() {
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

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<StudentDTO> getChilds() {
		return childs;
	}

	public void setChilds(List<StudentDTO> childs) {
		this.childs = childs;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
