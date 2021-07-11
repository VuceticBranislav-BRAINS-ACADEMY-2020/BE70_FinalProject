package com.iktakademija.FinalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Secured("ROLE_USER")
	@RequestMapping(path = "/api/v1/users", method = RequestMethod.GET)
	public ResponseEntity<?> findAllUsers() {
		
		//return new ResponseEntity<RESTMessage>(new RESTMessage(ERESTMessageCodes.LIST_EMPTY), HttpStatus.OK);
		return new ResponseEntity<List<UserEntity>>((List<UserEntity>) userRepository.findAll(), HttpStatus.OK);
	}
}
