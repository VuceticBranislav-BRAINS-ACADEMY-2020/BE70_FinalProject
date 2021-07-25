package com.iktakademija.FinalProject.controllers;

import java.util.Optional;

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

/**
 * Login endpoint.
 * <BR>Provide token for registred user based on username and password.
 * @see #login
 */
@RestController
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginService loginService;
	
	/**
	 *  Request authorization token from server.
	 *  <BR>User must be in database in order to get token.
	 *  
	 * @param username registred in database
	 * @param password registred in database
	 * @return {@link HttpStatus.OK} when token is granted, {@link HttpStatus.UNAUTHORIZED} otherwise. 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
		// Find user by username	
		Optional<UserEntity> op = userRepository.findByUsername(username);
		if (op.isPresent() == false) new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		UserEntity userEntity = op.get();
		
		// Check password
		if (userEntity != null && Encryption.validatePassword(password, userEntity.getPassword())) {			
			String token = loginService.getJWTToken(userEntity); // Provide token	
			UserTokenDTO retVal = new UserTokenDTO(username, "Bearer " + token);
			return new ResponseEntity<UserTokenDTO>(retVal, HttpStatus.OK); 
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
