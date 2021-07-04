package com.iktakademija.FinalProject.entities;

public class TeacherEntity {

	private IdentityEntity identity;
	private UserEntity user;

	public TeacherEntity() {
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
