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
 * Teacher controller.<BR>
 * Provide all teacher functionalities.
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

	/**
	 * REST endpoint that returns all teachers from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * <BR>
	 * 
	 * Postman code: <B>TEA10</B>
	 * 
	 * @return set of {@link TeacherDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllTeachers() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.getAllTeachers()", Level.INFO);

		// Get list of all users
		List<TeacherDTO> retVal = teacherService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<TeacherDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * REST endpoint that returns teacher from data base by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * <BR>
	 * 
	 * Postman code: <B>TEA11</B>
	 * 
	 * @return {@link TeacherDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable(value = "id") Integer teacherId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.getTeacherById()", Level.INFO);

		// Check teacher id
		if (teacherId == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		TeacherDTO dto = teacherService.getTeacherDTO(teacherId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new teacher to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * Postman code: <B>TEA01</B>
	 * 
	 * @return Added teacher if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addTeacher(@RequestBody NewTeacherDTO newUser) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.addTeacher()", Level.INFO);

		TeacherEntity teacher = teacherService.createTeacher(newUser);
		if (teacher == null) {
			loggingService.loggTwoOutMessage("Teacher can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<TeacherDTO>(teacherService.createDTO(teacher), HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing teacher entity
	 * 
	 * Postman code: <B>TEA12</B>
	 * 
	 * @return changed teacher entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setTeacher(@PathVariable(value = "id") Integer teacherId,
			@RequestBody NewTeacherDTO newTeacher) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.setTeacher()", Level.INFO);

		if (teacherId == null || newTeacher == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id or new teacher data.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Change existing teacher
		TeacherDTO dto = teacherService.setTeacher(teacherId, newTeacher);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for removing teacher entity.
	 * 
	 * Postman code: <B>TEA13</B>
	 * 
	 * @return removed teacher entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeTeacher(@PathVariable(value = "id") Integer teacherId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.removeTeacher()", Level.INFO);

		// Find teacher by id
		if (teacherId == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		TeacherDTO dto = teacherService.removeTeacher(teacherId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<TeacherDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Return all grades related to teacher
	 * 
	 * Postman code: <B>TEA20</B>
	 * 
	 * @return removed address entity.
	 */
	@Secured("ROLE_TEACHER")
	@JsonView(value = Views.Teacher.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getallgrades")
	public ResponseEntity<?> getAllGrades() {

		// Logging and retriving user informations
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
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
		} else {
			loggingService.loggMessage("NOT Authorized to access.", Level.WARN);
			loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.ACCESS_NOT_ALLOWED),
					HttpStatus.BAD_REQUEST);
		}

		// Grant informations to valid user
		loggingService.loggMessage("Request granted.", Level.INFO);
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);

		List<GradeDTO> dto = teacherService.findAllGradesForStudentsAndSubjects(teacher);

		return new ResponseEntity<List<GradeDTO>>(dto, HttpStatus.OK);
	}

}
