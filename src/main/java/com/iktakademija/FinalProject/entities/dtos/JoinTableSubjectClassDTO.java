package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.securities.Views;

/**
 * JoinTableSubjectClass DTO. <BR>
 * Provide all information related to {@link JoinTableSubjectClass}.
 * 
 * @see JoinTableSubjectClass
 * @author GM
 */
@JsonView(value = Views.Admin.class)
@JsonPropertyOrder(value = { "id", "subject", "clazz", "fond" })
public class JoinTableSubjectClassDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@JsonProperty(value = "ID")
	private Integer id;	
	
	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Subject")
	private Integer subject;

	@Positive(message = "Must be positiv index number.")
	@JsonProperty(value = "ID Class")
	private Integer clazz;

	@Positive(message = "Must be positiv number.")
	@JsonProperty(value = "Fond")
	private Integer fond;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public JoinTableSubjectClassDTO() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public Integer getSubject() {
		return subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

}
