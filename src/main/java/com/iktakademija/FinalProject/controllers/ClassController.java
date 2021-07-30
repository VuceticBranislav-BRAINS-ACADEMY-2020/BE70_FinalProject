package com.iktakademija.FinalProject.controllers;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.dtos.ClassDTO;
import com.iktakademija.FinalProject.entities.dtos.JoinTableSubjectClassDTO;
import com.iktakademija.FinalProject.entities.dtos.NewClassDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.ClassService;
import com.iktakademija.FinalProject.services.JoinTableSubjectClassService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;

/**
 * Class controller.<BR>
 * Provide all class functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/class")
public class ClassController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private ClassService classService;

	@Autowired
	private JoinTableSubjectClassService joinTableSubjectClassService;

	/**
	 * REST endpoint that returns all class from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>CLS10</B>
	 * 
	 * @return set of {@link ClassDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllClasses() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ClassController.getAllClasses()", Level.INFO);

		// Get list of all users
		List<ClassDTO> retVal = classService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<ClassDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * REST endpoint that returns class from data base by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>CLS11</B>
	 * 
	 * @return {@link ClassDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getClassById(@PathVariable(value = "id") Integer classId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ClassController.getClassById()", Level.INFO);

		// Check class id
		if (classId == null) {
			loggingService.loggTwoOutMessage("Invalid class id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		ClassDTO dto = classService.getClassDTO(classId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Class not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ClassDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new class to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * 
	 * Postman code: <B>CLS01</B>
	 * 
	 * @return Added class if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addClass(@Valid @RequestBody NewClassDTO newClass) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ClassController.addClass()", Level.INFO);

		ClassEntity clazz = classService.createClass(newClass);
		if (clazz == null) {
			loggingService.loggTwoOutMessage("Class can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ClassDTO>(classService.createDTO(clazz), HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing class entity
	 * 
	 * Postman code: <B>CLA12</B>
	 * 
	 * @return changed class entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setClass(@PathVariable(value = "id") Integer classId, @RequestBody NewClassDTO newClass) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ClassController.setClass()", Level.INFO);

		if (classId == null || newClass == null) {
			loggingService.loggTwoOutMessage("Invalid class id or new teacher data.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Change existing teacher
		ClassDTO dto = classService.setClass(classId, newClass);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Class not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ClassDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for removing class entity.
	 * 
	 * Postman code: <B>CLS13</B>
	 * 
	 * @return removed teacher entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeClass(@PathVariable(value = "id") Integer classId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ClassController.removeClass()", Level.INFO);

		// Find teacher by id
		if (classId == null) {
			loggingService.loggTwoOutMessage("Invalid class id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		ClassDTO dto = classService.removeClass(classId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ClassDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add subject to class. Postman code: <B>CLS14</B>
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{classid}/{subjectid}/{fond}")
	public ResponseEntity<?> addSubjectToClass(@PathVariable("subjectid") Integer subjectid,
			@PathVariable("classid") Integer classid, @PathVariable("fond") Integer fond) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: SubjectController.addSubjectToClass()", Level.INFO);

		JoinTableSubjectClass item = classService.addSubjectToClass(subjectid, classid, fond);
		if (item == null) {
			loggingService.loggTwoOutMessage("Subject can not be added to class. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableSubjectClassDTO>(joinTableSubjectClassService.createDTO(item),
				HttpStatus.OK);
	}

}
