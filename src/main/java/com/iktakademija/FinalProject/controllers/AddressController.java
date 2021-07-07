package com.iktakademija.FinalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.repositories.AddressRepositories;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

	@Autowired
	private AddressRepositories addressRepositories;

	/**
	 * REST endpoint that returns all addresses from data base. 
	 * Method always return {@link HttpStatus.OK} if there is no internal error.<BR><BR>
	 * REST method: GET, path: ""<BR>
	 * Error status messages: none<BR>
	 * Postman identification tag: <B>ADR01</B>
	 * @return list of {@link AddressEntity} from database or empty list if nothing to return.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllAdresses() {
		List<AddressEntity> ar = addressRepositories.findAll();
		return new ResponseEntity<List<AddressEntity>>(ar, HttpStatus.OK);
	}
}
