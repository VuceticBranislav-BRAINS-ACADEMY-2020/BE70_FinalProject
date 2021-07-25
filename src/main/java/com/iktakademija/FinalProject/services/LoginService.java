package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.UserEntity;

public interface LoginService {
	
	String getJWTToken(UserEntity userEntity);
	
	/**
	 * Finds teacher entity 
	 * 
	 * @return Return {@link TeacherEntity} from login credentials or null if fail.
	 */
	TeacherEntity getTeacherFromLogIn();
	
	/**
	 * Finds administrator entity 
	 * 
	 * @return Return {@link AdminEntity} from login credentials or null if fail.
	 */
	AdminEntity getAdminFromLogIn();

	UserEntity getUser();
	
}
