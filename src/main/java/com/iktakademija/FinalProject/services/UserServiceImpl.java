package com.iktakademija.FinalProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.repositories.UserRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean changeUsernameAndPasswor(UserEntity user, String username, String pass) {

		// Check is username unused
		Optional<UserEntity> op = userRepository.findByUsername(username);
		if (op.isPresent() == true)
			return false;

		// Generate password
		String password = Encryption.passwordEncode(pass);

		// Set username and password
		user.setPassword(password);
		user.setUsername(username);

		// Save user in database
		userRepository.save(user);
		return true;
	}

}
