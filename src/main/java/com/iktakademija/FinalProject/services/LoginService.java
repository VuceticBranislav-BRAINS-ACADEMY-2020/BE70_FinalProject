package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.UserEntity;

public interface LoginService {
	
	String getJWTToken(UserEntity userEntity);
	
}
