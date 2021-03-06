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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.dtos.NewParentDTO;
import com.iktakademija.FinalProject.dtos.ParentDTO;
import com.iktakademija.FinalProject.dtos.StudentDTO;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.GradeService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.services.ParentService;
import com.iktakademija.FinalProject.services.UserService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

/**
 * Parent endpoint. <BR>
 * Provide all parent functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/parent")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private ParentService parentService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private UserService userService;

	@Autowired
	private GradeService gradeService;

	// PAR10
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllParents() {
		return new ResponseEntity<List<ParentDTO>>(parentService.getDTOList(), HttpStatus.OK);
	}

	// PAR11
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getParentsById(@PathVariable(value = "id") Integer parentID) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.getParentsById()", Level.INFO);

		if (parentID == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		ParentDTO dto = parentService.getParentDTO(parentID);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new parent to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/parent/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>PAR01</B>
	 * 
	 * @return Added parent if there is no errors.
	 * @see ParentController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addParent(@Valid @RequestBody NewParentDTO newUser) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.addParent()", Level.INFO);

		ParentEntity user2 = parentService.createParent(newUser);
		user2 = parentRepository.save(user2);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(parentService.createDTO(user2), HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing parent entity
	 * 
	 * Postman code: <B>PAR12</B>
	 * 
	 * @return changed parent entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setParent(@PathVariable(value = "id") Integer parentId,
			@RequestBody NewParentDTO newParent) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.setParent()", Level.INFO);

		if (parentId == null || newParent == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		ParentDTO dto = parentService.setParent(parentId, newParent);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(dto, HttpStatus.OK);

	}

	/**
	 * REST endpoint for removing parent entity.
	 * 
	 * Postman code: <B>PAR13</B>
	 * 
	 * @return removed parent entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeParent(@PathVariable(value = "id") Integer parentId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.removeParent()", Level.INFO);

		if (parentId == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		ParentDTO dto = parentService.removeParent(parentId);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add child to parent
	 * 
	 * Postman code: <B>PAR14</B>
	 * 
	 * @return removed parent entity.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/child")
	public ResponseEntity<?> addChild(@RequestParam("parent") Integer parentID,
			@RequestParam("child") Integer childID) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.addChild()", Level.INFO);

		if (parentID == null || childID == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);

		Optional<ParentEntity> opp = parentRepository.findById(parentID);
		if (opp.isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		ParentEntity parent = opp.get();

		Optional<StudentEntity> ops = studentRepository.findById(childID);
		if (ops.isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		StudentEntity student = ops.get();

		JoinTableStudentParent join = new JoinTableStudentParent(parent, student);
		join = joinTableStudentParentRepository.save(join);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(parentService.createDTO(parent), HttpStatus.OK);
	}

	/**
	 * Get all child from parent
	 * 
	 * Postman code: <B>PAR15</B>
	 * 
	 * @return removed parent entity.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/admin/getchilds/{id}")
	public ResponseEntity<?> getAllChildsForParent(@PathVariable(value = "id") Integer parentID) {
		Optional<ParentEntity> op = parentRepository.findById(parentID);
		if (op.isPresent() == false)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		ParentEntity parent = op.get();
		List<StudentDTO> students = parentService.getAllChildrens(parent);
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}

	/**
	 * Parent preview general informations.<BR>
	 * Postman code: <B>PAR20</B>
	 */
	@Secured("ROLE_PARENT")
	@JsonView(value = Views.Parent.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public ResponseEntity<?> getParentInfo() {

		// Logging and retriving user informations
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.getParentInfo", Level.INFO);

		// Find out is student valid
		boolean isValid = false;
		ParentEntity parent = null;
		if (user instanceof ParentEntity) {
			parent = (ParentEntity) user;
			if (parent.getStatus().equals(EStatus.ACTIVE)) {
				isValid = true;
			}
		}

		// Logg validation status of student and exit if invalid
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
		ParentDTO dto = parentService.createDTO(parent);
		return new ResponseEntity<ParentDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Change password.<BR>
	 * Postman code: <B>PAR30</B>
	 */
	@Secured("ROLE_PARENT")
	@JsonView(value = Views.Parent.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/changecredentials")
	public ResponseEntity<?> changeUsernameAndPassword(@RequestParam("user") String newUsername,
			@RequestParam("pass") String newPassword) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.changeUsernamAndPassword()", Level.INFO);

		// Check id
		Optional<ParentEntity> op = parentRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		ParentEntity parent = op.get();

		// Check is password and username successful
		if (userService.changeUsernameAndPasswor(parent, newUsername, newPassword) == false) {
			loggingService.loggTwoOutMessage("Password or username can not be changed.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		;

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<ParentDTO>(parentService.createDTO(parent), HttpStatus.OK);
	}

	/**
	 * Filter all grades
	 * 
	 * Postman code: <B>PAR40</B>
	 */
	@Secured("ROLE_PARENT")
	@JsonView(value = Views.Student.class)
	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public ResponseEntity<?> searchAll(@RequestParam(required = false, name = "StudentId") Integer studentId,
			@RequestParam(required = false, name = "SubjectId") Integer subjectId,
			@RequestParam(required = false, name = "TeacherId") Integer teacherId,
			@RequestParam(required = false, name = "GroupId") Integer groupId,
			@RequestParam(required = false, name = "ClassId") Integer classId,
			@RequestParam(required = false, name = "Stage") EStage stage) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: ParentController.searchAll()", Level.INFO);

		// Check id
		Optional<ParentEntity> op = parentRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		ParentEntity parent = op.get();

		// Check childs
		boolean childValid = false;
		for (JoinTableStudentParent child : parent.getStudents()) {
			if (child.getStudent().getId().equals(studentId))
				;
		}
		if (childValid == false) {
			loggingService.loggTwoOutMessage("Childs do not belong to teacher.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_CHILD), HttpStatus.BAD_REQUEST);
		}

		// Get list of all users on page
		List<GradeEntity> list = gradeService.getFiltered(studentId, subjectId, teacherId, groupId, classId, stage);
		List<GradeDTO> retVal = gradeService.createDTOList(list);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}
}
