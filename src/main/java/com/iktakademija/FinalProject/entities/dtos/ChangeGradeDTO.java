package com.iktakademija.FinalProject.entities.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.enums.EGradeType;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

/**
 * Change grade DTO. <BR>
 * Provide all information needed to change existing grade.
 * 
 * @see GradeEntity
 * @author GM
 */
@JsonPropertyOrder(value = { "type", "value", "stage", "state"})
@JsonView(value = Views.Teacher.class)
public class ChangeGradeDTO {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@NotNull(message = "Value must be existing grade id.")
	@JsonProperty(value = "ID")
	private Integer id;
	
	@NotNull(message = "Grade type must be: EXAM, WRITTEN, ORAL or FINAL.")
	@JsonProperty(value = "Type")
	private EGradeType type;
	
	@NotNull(message = "Pleas enter grade value foomr 1 to 5.")
	@Min(value = 1, message = "Grade must be at least 1.")
	@Max(value = 5, message = "Grade can be 5 or less.")
	@JsonProperty(value = "Value")
	private Integer value;
	
	@NotNull(message = "Stage must be FIRST or SECOND.")
	@JsonProperty(value = "Stage")
	private EStage stage;	

	@NotNull(message = "State must be ACTIVE or DELETED.")
	@JsonProperty(value = "State")
	private EStatus state;	
	
	/************************************************************
	 * Constructors
	 ************************************************************/

	public ChangeGradeDTO() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public EGradeType getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(EGradeType type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public EStage getStage() {
		return stage;
	}

	public void setStage(EStage stage) {
		this.stage = stage;
	}

	public EStatus getState() {
		return state;
	}

	public void setState(EStatus state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ChangeGradeDTO [id=" + id + ", type=" + type + ", value=" + value + ", stage=" + stage + ", state="
				+ state + "]";
	}
	
}
