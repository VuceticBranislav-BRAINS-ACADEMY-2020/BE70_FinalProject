package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.UserEntity;

public interface LoginService {
	
	public String getJWTToken(UserEntity userEntity);
	
}
