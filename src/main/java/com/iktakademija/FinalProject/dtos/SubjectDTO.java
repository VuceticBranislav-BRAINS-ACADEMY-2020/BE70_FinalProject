package com.iktakademija.FinalProject.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Subject DTO.
 * <BR>Provide all information related to {@link SubjectEntity}
 * @see SubjectEntity
 * @author GM
 */
@JsonView(value = Views.Student.class)
@JsonPropertyOrder(value =  {"id", "name", "fond", "version", "status"})
public class SubjectDTO {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	@JsonView(value = Views.Teacher.class)
	@JsonProperty(value = "ID")
	private Integer id;	
	
	@JsonProperty(value = "Name")
	private String name;

	@JsonView(value = Views.Teacher.class)
	@JsonProperty(value = "Fond")
	private Integer fond;
	
	@JsonView(value = Views.Admin.class)
	@JsonProperty(value = "Version")
	private Integer version;
	
	@JsonProperty(value = "Status")
	private EStatus status;
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public SubjectDTO() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
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

}
