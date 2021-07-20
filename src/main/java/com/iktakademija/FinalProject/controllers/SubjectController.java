package com.iktakademija.FinalProject.controllers;

import java.util.List;

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
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.dtos.NewSubjectDTO;
import com.iktakademija.FinalProject.entities.dtos.SubjectDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.SubjectService;

/**
 * Subject endpoint.
 * <BR>Provide all subject functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/subject")
public class SubjectController {	
	
	@Autowired
	private SubjectService subjectService;
	
	// SUB10
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllSubjects() {
		return new ResponseEntity<List<SubjectDTO>>(subjectService.getDTOList(), HttpStatus.OK);
	}
	
	// SUB11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable(value = "id") Integer subjectId) {
		if (subjectId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		SubjectDTO dto = subjectService.getSubjectDTO(subjectId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);		
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}
	
	// SUB12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setSubject(@PathVariable(value = "id") Integer subjectId, @RequestBody NewSubjectDTO newSubject) {
		if (subjectId == null || newSubject == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		SubjectDTO dto = subjectService.setSubject(subjectId, newSubject);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}
	
	// SUB13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeSubject(@PathVariable(value = "id") Integer subjectId) {
		if (subjectId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		SubjectDTO dto = subjectService.removeSubject(subjectId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}
	
	// SUB01
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addSubject(@RequestBody NewSubjectDTO newSubject) {
		SubjectEntity user = subjectService.createSubject(newSubject);
		if (user == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<SubjectDTO>(subjectService.createDTO(user), HttpStatus.OK);
	}
}
