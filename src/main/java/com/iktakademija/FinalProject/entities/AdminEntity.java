package com.iktakademija.FinalProject.entities;

public class AdminEntity {

	private IdentityEntity identity;
	private UserEntity user;
	private Boolean superAdmin;

	public AdminEntity() {
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

	public Boolean getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

}
