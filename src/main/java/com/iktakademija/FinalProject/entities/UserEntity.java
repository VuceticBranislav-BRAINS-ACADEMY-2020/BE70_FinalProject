package com.iktakademija.FinalProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "idperson", "idrole" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonView(value = {Views.Admin.class})
public class UserEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	protected String password;

	@Column(nullable = false, unique = true)
	protected String username;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idperson", nullable = false)
	@JsonBackReference(value = "User_Person_1")
	protected PersonEntity person;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idrole", nullable = false)
	@JsonBackReference(value = "Role_User_1")
	protected RoleEntity role;

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	@Version
	protected Integer version;

	@Column
	@Enumerated(value = EnumType.STRING)
	protected EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password, PersonEntity person, RoleEntity role) {
		super();
		this.password = password;
		this.username = username;
		this.person = person;
		this.role = role;
		this.status = EStatus.ACTIVE;
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

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
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

}
