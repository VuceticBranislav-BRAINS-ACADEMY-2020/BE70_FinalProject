package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.UserEntity;

public interface UserService {

	boolean changeUsernameAndPasswor(UserEntity user, String username, String pass);

}
