package com.iktakademija.FinalProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class RoleEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(name = "rolename", unique = true, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ERole role;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "Role_User_1")
	private Set<UserEntity> users = new HashSet<>();

	/************************************************************
	 * Shadow Attributes
	 ************************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	@Column
	@Enumerated(value = EnumType.STRING)
	private EStatus status;

	/************************************************************
	 * Constructors
	 ************************************************************/

	public RoleEntity() {
		super();
	}
	
	/************************************************************
	 * Getters & Setters
	 ************************************************************/
	
	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
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
