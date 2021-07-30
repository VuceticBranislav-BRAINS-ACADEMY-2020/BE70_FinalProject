package com.iktakademija.FinalProject.entities;

import java.time.LocalDate;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@JsonView(value = Views.Admin.class)
public class PersonEntity {

	/************************************************************
	 * Attributes
	 ************************************************************/

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false, unique = true)
	private String jmbg;

	@Column(nullable = false)
	private String mphone;

	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthdate;

	/************************************************************
	 * Relation Attributes
	 ************************************************************/

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idaddress", nullable = false)
	@JsonBackReference(value = "Person_Address_1")
	private AddressEntity address;

	@JsonIgnore
	@OneToMany(mappedBy = "person", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "User_Person_1")
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

	public PersonEntity() {
		super();
	}

	/************************************************************
	 * Getters & Setters
	 ************************************************************/

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	@JsonIgnore
	public Set<UserEntity> getUsers() {
		return users;
	}

	@JsonIgnore
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
