package com.iktakademija.FinalProject.controllers;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;

@RestController
@RequestMapping(path = "/api/v1/jtsp")
public class JoinTableStudentParentController {


	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;
	
	/**
	 * Endpoint to get all join table entities.<BR>
	 * Postman code: <B>JSP10</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllEntities() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableStudentParentController.getAllEntities()", Level.INFO);

		// Get all entity
		List<JoinTableStudentParent> retVal = joinTableStudentParentRepository.findAll();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<JoinTableStudentParent>>(retVal, HttpStatus.OK);
	}

	/**
	 * Endpoint to get join table entity by id.<BR>
	 * Postman code: <B>JSP11</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getEntity(@PathVariable(value = "id") Integer Id) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableStudentParentController.getAllEntities()", Level.INFO);
		
		// Check id
		if (Id == null) {
			loggingService.loggTwoOutMessage("Invalid index.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		
		// Get entity
		Optional<JoinTableStudentParent> retVal = joinTableStudentParentRepository.findById(Id);
		if (retVal == null) {
			loggingService.loggTwoOutMessage("Entity not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		
//		retVal.get
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableStudentParent>(retVal.get(), HttpStatus.OK);
	}
}
