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
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.dtos.GradeDTO;
import com.iktakademija.FinalProject.entities.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.services.TeacherService;

/**
 * Teacher endpoint.
 * <BR>Provide all teacher functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoggingService loggingService;
		
	// TEA10
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllTeachers() {
		return new ResponseEntity<List<TeacherDTO>>(teacherService.getDTOList(), HttpStatus.OK);
	}
	
	// TEA11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable(value = "id") Integer teacherId) {
		if (teacherId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		TeacherDTO dto = teacherService.getTeacherDTO(teacherId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);		
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);	
	}
	
	// TEA12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setTeacher(@PathVariable(value = "id") Integer teacherId, @RequestBody NewTeacherDTO newTeacher) {
		if (teacherId == null || newTeacher == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		TeacherDTO dto = teacherService.setTeacher(teacherId, newTeacher);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);	
	}
	
	// TEA13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeTeacher(@PathVariable(value = "id") Integer teacherId) {
		if (teacherId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		TeacherDTO dto = teacherService.removeTeacher(teacherId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);	
	}
	
	/**
	 * Add new teacher to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/teacher/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>TEA01</B>
	 * @return Added teacher if there is no errors.
	 * @see TeacherController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addTeacher(@RequestBody NewTeacherDTO newUser) {
		TeacherEntity user = teacherService.createTeacher(newUser);
		if (user == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<TeacherDTO>(teacherService.createDTO(user), HttpStatus.OK);
	}
	
	//TEA20
	@Secured("ROLE_TEACHER")
	@JsonView(value = Views.Teacher.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getallgrades")
	public ResponseEntity<?> getAllGrades() {		
		
		// Logging and retriving user informations
		UserEntity user = loginService.getUser();
		loggingService.getRoleAndLogg(user, Level.INFO);	
		loggingService.loggMessage("Method: TeacherController.getAllGrades", Level.INFO);	
		
		// Find out is student valid
		boolean isValid = false;
		TeacherEntity teacher = null;
		if (user instanceof TeacherEntity) {
			teacher = (TeacherEntity) user;
			if (teacher.getStatus().equals(EStatus.ACTIVE)) {
				isValid = true;
			}
		}	
		
		// Logg validation status of teacher and exit if invalid
		if (isValid) {
			loggingService.loggMessage("Authorized to access.", Level.INFO);
		}
		else {
			loggingService.loggMessage("NOT Authorized to access.", Level.WARN);
			loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.ACCESS_NOT_ALLOWED), HttpStatus.BAD_REQUEST);	
		}		
		
		// Grant informations to valid user
		loggingService.loggMessage("Request granted.", Level.INFO);
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		
		List<GradeDTO> dto = teacherService.findAllGradesForStudentsAndSubjects(teacher);	
		
		return new ResponseEntity<List<GradeDTO>>(dto, HttpStatus.OK);	
	}
	
}
