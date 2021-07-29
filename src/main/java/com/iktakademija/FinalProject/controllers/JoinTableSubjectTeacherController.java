package com.iktakademija.FinalProject.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.dtos.JoinTableSubjectTeacherDTO;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectTeacherRepository;
import com.iktakademija.FinalProject.services.JoinTableSubjectTeacherService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;

@RestController
@RequestMapping(path = "/api/v1/jtst")
public class JoinTableSubjectTeacherController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private JoinTableSubjectTeacherRepository joinTableSubjectTeacherRepository;

	@Autowired
	private JoinTableSubjectTeacherService joinTableSubjectTeacherService;

	/**
	 * Endpoint to get all join table entities.<BR>
	 * Postman code: <B>JST10</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllEntities() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableSubjectTeacherController.getAllEntities()", Level.INFO);

		// Get all entity
		List<JoinTableSubjectTeacherDTO> retVal = joinTableSubjectTeacherService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<JoinTableSubjectTeacherDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * Endpoint to get join table entity by id.<BR>
	 * Postman code: <B>JST11</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getEntity(@PathVariable(value = "id") Integer Id) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableSubjectTeacherController.getEntity()", Level.INFO);

		// Check id
		if (Id == null) {
			loggingService.loggTwoOutMessage("Invalid index.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Get entity
		Optional<JoinTableSubjectTeacher> dto = joinTableSubjectTeacherRepository.findById(Id);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Entity not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		JoinTableSubjectTeacherDTO retVal = joinTableSubjectTeacherService.createDTO(dto.get());
		return new ResponseEntity<JoinTableSubjectTeacherDTO>(retVal, HttpStatus.OK);
	}

	/**
	 * Endpoint to create table entity.<BR>
	 * Postman code: <B>JST01</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, path = "")
	public ResponseEntity<?> createEntity(@Valid @RequestBody JoinTableSubjectTeacherDTO entity) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableSubjectTeacherController.createEntity()", Level.INFO);

		JoinTableSubjectTeacher retVal = joinTableSubjectTeacherService.createEntity(entity);
		if (retVal == null) {
			loggingService.loggTwoOutMessage("Join can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableSubjectTeacherDTO>(joinTableSubjectTeacherService.createDTO(retVal),
				HttpStatus.OK);
	}

	/**
	 * Endpoint to change table entity.<BR>
	 * Postman code: <B>JST12</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> setEntity(@PathVariable(value = "id") Integer id,
			@RequestBody JoinTableSubjectTeacherDTO entity) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableSubjectTeacherController.setEntity()", Level.INFO);

		// Retrive DTO and change
		JoinTableSubjectTeacherDTO retVal = joinTableSubjectTeacherService.setEntity(id, entity);
		if (retVal == null) {
			loggingService.loggTwoOutMessage("Address not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableSubjectTeacherDTO>(retVal, HttpStatus.OK);
	}

	/**
	 * Endpoint to delete table entity.<BR>
	 * Postman code: <B>JST13</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<?> removeEntity(@PathVariable(value = "id") Integer id) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: JoinTableSubjectTeacherController.removeEntity()", Level.INFO);

		if (id == null) {
			loggingService.loggTwoOutMessage("Invalid address id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		JoinTableSubjectTeacherDTO retVal = joinTableSubjectTeacherService.removeEntity(id);
		if (retVal == null) {
			loggingService.loggTwoOutMessage("Address not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableSubjectTeacherDTO>(retVal, HttpStatus.OK);
	}
}
