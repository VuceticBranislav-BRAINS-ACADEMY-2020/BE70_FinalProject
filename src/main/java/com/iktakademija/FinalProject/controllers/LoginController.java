package com.iktakademija.FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.controllers.dto.UserTokenDTO;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.repositories.UserRepository;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.utils.Encryption;

@RestController
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
		// Find user by username		
		UserEntity userEntity = userRepository.findByUsername(username);	
		// Check password
		if (userEntity != null && Encryption.validatePassword(password, userEntity.getPassword())) {			
			String token = loginService.getJWTToken(userEntity); // Provide token	
			UserTokenDTO retVal = new UserTokenDTO(username, "Bearer " + token);
			return new ResponseEntity<UserTokenDTO>(retVal, HttpStatus.OK); 
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
