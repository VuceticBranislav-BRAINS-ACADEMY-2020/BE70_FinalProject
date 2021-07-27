package com.iktakademija.FinalProject.controllers;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.dtos.NewSubjectDTO;
import com.iktakademija.FinalProject.entities.dtos.SubjectDTO;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
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
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	
	/**
	 * REST endpoint that returns all subjects from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>SUB10</B>
	 * 
	 * @return set of {@link TeacherDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllSubjects() {
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.getAllSubjects()", Level.INFO);
		
		// Get list of all users
		List<SubjectDTO> retVal = subjectService.getDTOList();
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<SubjectDTO>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * REST endpoint that returns subjects from data base by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>SUB11</B>
	 * 
	 * @return {@link TeacherDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable(value = "id") Integer subjectId) {
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.getSubjectById()", Level.INFO);
		
		if (subjectId == null) {
			loggingService.loggTwoOutMessage("Invalid subject id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);		
		}
		
		SubjectDTO dto = subjectService.getSubjectDTO(subjectId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Subject not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);		
		}
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}
	
	/**
	 * Add new subject to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * 
	 * Postman code: <B>SUB01</B>
	 * 
	 * @return Added subject if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addSubject(@RequestBody NewSubjectDTO newSubject) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.addSubject()", Level.INFO);
		
		SubjectEntity subject = subjectService.createSubject(newSubject);
		if (subject == null ) {
			loggingService.loggTwoOutMessage("Subject can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);
		}
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<SubjectDTO>(subjectService.createDTO(subject), HttpStatus.OK);
	}	
	
	/**
	 * REST endpoint for changing subject entity
	 * 
	 * Postman code: <B>SUB12</B>
	 * 
	 * @return changed subject entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setSubject(@PathVariable(value = "id") Integer subjectId, @RequestBody NewSubjectDTO newSubject) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.setSubject()", Level.INFO);
		
		if (subjectId == null || newSubject == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id or new teacher data.",
				HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);			
		}
		
		SubjectDTO dto = subjectService.setSubject(subjectId, newSubject);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
				Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		}
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}
	
	/**
	 * REST endpoint for removing subject entity.
	 * 
	 * Postman code: <B>SUB13</B>
	 * 
	 * @return removed teacher entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeSubject(@PathVariable(value = "id") Integer subjectId) {
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.removeSubject()", Level.INFO);
		
		if (subjectId == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.BAD_REQUEST);			
		}
		
		SubjectDTO dto = subjectService.removeSubject(subjectId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Subject not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
				Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		
		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<SubjectDTO>(dto, HttpStatus.OK);	
	}	

}
