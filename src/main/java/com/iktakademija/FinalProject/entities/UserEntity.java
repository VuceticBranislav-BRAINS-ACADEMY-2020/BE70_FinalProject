package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity(name = "user")
//@Table(name = "user")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String username;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "identity")
	@JsonBackReference(value = "User_Identity_1")
	private IdentityEntity identity;

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	@Column
	private EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public UserEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public IdentityEntity getIdentity() {
		return identity;
	}

	public void setIdentity(IdentityEntity identity) {
		this.identity = identity;
	}

}
