package com.iktakademija.FinalProject.entities;

public class ParentEntity {

	private IdentityEntity identity;
	private UserEntity user;
	private StudentEntity student;

	public ParentEntity() {
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

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

}
