package com.iktakademija.FinalProject.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.dtos.NewPersonDTO;
import com.iktakademija.FinalProject.entities.dtos.PersonDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.PersonService;

@RestController
@RequestMapping(path = "/api/v1/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	// PER10
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllPersons() {
		return new ResponseEntity<List<PersonDTO>>(personService.getDTOList(), HttpStatus.OK);
	}
	
	// PER11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable(value = "id") Integer personId) {
		if (personId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);		
		PersonDTO dto = personService.getPersonDTO(personId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);		
		return new ResponseEntity<PersonDTO>(dto, HttpStatus.OK);	
	}
	
	// PER12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setPerson(@PathVariable(value = "id") Integer personId, @RequestBody NewPersonDTO newPerson) {
		if (personId == null || newPerson == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);			
		PersonDTO dto = personService.setPerson(personId, newPerson);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<PersonDTO>(dto, HttpStatus.OK);	
	}
	
	// PER13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removePerson(@PathVariable(value = "id") Integer personId) {
		if (personId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);			
		PersonDTO dto = personService.removePerson(personId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<PersonDTO>(dto, HttpStatus.OK);	
	}

	/************************************************************
	 * Admin
	 ************************************************************/
	
	/**
	 * Add new person to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin/person"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>PER01</B>
	 * @return Added person if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addPerson(@Valid @RequestBody NewPersonDTO newPerson) {	
		
		PersonEntity person = personService.createPerson(newPerson);	
		if (person == null ) {
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PersonEntity>(person, HttpStatus.OK);
	}
	
}
