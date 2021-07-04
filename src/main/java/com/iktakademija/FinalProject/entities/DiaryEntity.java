package com.iktakademija.FinalProject.entities;

import com.iktakademija.FinalProject.entities.enums.EStage;

public class DiaryEntity {

	private String name;
	private String site;
	private Integer currentGeneration;
	private EStage currentStage;
	private AddressEntity address;

	public DiaryEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(Integer currentGeneration) {
		this.currentGeneration = currentGeneration;
	}

	public EStage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(EStage currentStage) {
		this.currentStage = currentStage;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

}
