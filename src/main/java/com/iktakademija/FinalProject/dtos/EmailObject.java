package com.iktakademija.FinalProject.dtos;

public class EmailObject {
	
	/************************************************************
	 * Attributes
	 ************************************************************/
	
	private String[] to;
	private String subject;
	private String text;
	
	/************************************************************
	 * Constructors
	 ************************************************************/
	
	public EmailObject() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	

}
