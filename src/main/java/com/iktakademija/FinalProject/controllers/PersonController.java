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
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.tests;

@RestController
@RequestMapping(path = "/api/v1/person")
public class PersonController {
	
	@Autowired
	private tests tee;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAdmins() {

		// return new ResponseEntity<RESTMessage>(new RESTMessage(ERESTMessageCodes.LIST_EMPTY), HttpStatus.OK);
		List<UserEntity> asd = tee.findByUsername2(EStatus.valueOf("ACTIVE"));
		return new ResponseEntity<List<UserEntity>>(asd, HttpStatus.OK);	
	}
	
}

// todo ako radi logovanj i ocenjivanje onda je verovatno ok.
