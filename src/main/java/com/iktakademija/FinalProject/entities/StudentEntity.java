package com.iktakademija.FinalProject.entities;

public class StudentEntity {

	private IdentityEntity identity;
	private UserEntity user;

	public StudentEntity() {
		super();
	}

	public IdentityEntity getIdentity() {
		return identity;
	}

	public void setIdentity(IdentityEntity identity) {
		this.identity = identity;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}