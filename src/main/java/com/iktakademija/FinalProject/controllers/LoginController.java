package com.iktakademija.FinalProject.controllers;

import java.util.Optional;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.dtos.UserTokenDTO;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.repositories.UserRepository;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.Encryption;
import com.iktakademija.FinalProject.utils.RESTError;

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
	
	@Autowired
	private LoggingService loggingService;
	
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
		
		// Logging try to get token
		loggingService.getLoggAccessToken(username, Level.INFO);
		
		// Find user by username	
		Optional<UserEntity> op = userRepository.findByUsername(username);
		if (op.isPresent() == false) {	
			loggingService.loggTwoOutMessage("User not found in data base.", HttpStatus.UNAUTHORIZED.toString(), Level.WARN);	
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.TOKEN_BAD_USERNAME), HttpStatus.UNAUTHORIZED);
		}
		UserEntity userEntity = op.get();
		
		// Check password
		if (userEntity != null && Encryption.validatePassword(password, userEntity.getPassword())) {			
			String token = loginService.getJWTToken(userEntity); // Provide token	
			UserTokenDTO retVal = new UserTokenDTO(username, "Bearer " + token);
			loggingService.loggTwoOutMessage("Token granted.", HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<UserTokenDTO>(retVal, HttpStatus.OK); 
		}
		loggingService.loggTwoOutMessage("Token NOT granted.", HttpStatus.UNAUTHORIZED.toString(), Level.WARN);
		return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.TOKEN_BAD_PASSWORD), HttpStatus.UNAUTHORIZED);
	}

}
