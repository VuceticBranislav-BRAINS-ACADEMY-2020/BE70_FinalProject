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
import com.iktakademija.FinalProject.dtos.JoinTableSubjectTeacherDTO;
import com.iktakademija.FinalProject.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.dtos.SubjectDTO;
import com.iktakademija.FinalProject.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.GradeService;
import com.iktakademija.FinalProject.services.JoinTableSubjectTeacherService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.services.SubjectService;
import com.iktakademija.FinalProject.services.TeacherService;
import com.iktakademija.FinalProject.services.UserService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

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

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private JoinTableSubjectTeacherService joinTableSubjectTeacherService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private GroupRepository groupRepository;

	/**
	 * REST endpoint that returns all teachers from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
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
	 * 
	 * Postman code: <B>TEA01</B>
	 * 
	 * @return Added teacher if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addTeacher(@Valid @RequestBody NewTeacherDTO newTeacher) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.addTeacher()", Level.INFO);

		TeacherEntity teacher = teacherService.createTeacher(newTeacher);
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

	/**
	 * Change password.<BR>
	 * Postman code: <B>TEA30</B>
	 */
	@Secured("ROLE_TEACHER")
	@JsonView(value = Views.Teacher.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/changecredentials")
	public ResponseEntity<?> changeUsernameAndPassword(@RequestParam("user") String newUsername,
			@RequestParam("pass") String newPassword) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.changeUsernamAndPassword()", Level.INFO);

		// Check id
		Optional<TeacherEntity> op = teacherRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		TeacherEntity teacher = op.get();

		// Check is password and username successful
		if (userService.changeUsernameAndPasswor(teacher, newUsername, newPassword) == false) {
			loggingService.loggTwoOutMessage("Password or username can not be changed.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		;

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<TeacherDTO>(teacherService.createDTO(teacher), HttpStatus.OK);
	}

	/**
	 * Add teacher to subject. Postman code: <B>TEA15</B>
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{teacherid}/{subjectid}/{groupid}")
	public ResponseEntity<?> addTeacherToSubjectByClass(@PathVariable("teacherid") Integer teacherid,
			@PathVariable("subjectid") Integer subjectid, @PathVariable("groupid") Integer groupid) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.addTeacherToSubjectByClass()", Level.INFO);

		JoinTableSubjectTeacher item = joinTableSubjectTeacherService.addTeacherToSubjectByGroup(teacherid, subjectid,
				groupid);
		if (item == null) {
			loggingService.loggTwoOutMessage(
					String.format("Teacher [%s] on subject [%s] can not be added to group [%s]. Invalid data provided.",
							teacherid, subjectid, groupid),
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<JoinTableSubjectTeacherDTO>(joinTableSubjectTeacherService.createDTO(item),
				HttpStatus.OK);
	}

	/**
	 * Return all subjects of teacher.
	 * 
	 * Postman code: <B>TEA16</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/subjects/{id}")
	public ResponseEntity<?> getTeacherSubjectsById(@PathVariable(value = "id") Integer teacherId) {

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

		List<SubjectEntity> dto = teacherService.getAllSubjectsByTeacher(teacherId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<SubjectDTO>>(subjectService.createDTOList(dto), HttpStatus.OK);
	}

	/**
	 * Filter all grades
	 * 
	 * Postman code: <B>TEA40</B>
	 */
	@Secured("ROLE_TEACHER")
	@JsonView(value = Views.Student.class)
	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public ResponseEntity<?> searchAll(@RequestParam(name = "ClassMaster") Boolean classmaster,
			@RequestParam(required = false, name = "StudentId") Integer studentId,
			@RequestParam(required = false, name = "SubjectId") Integer subjectId,
			@RequestParam(required = false, name = "GroupId") Integer groupId,
			@RequestParam(required = false, name = "ClassId") Integer classId,
			@RequestParam(required = false, name = "Stage") EStage stage) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.searchAll()", Level.INFO);

		// Check id
		Optional<TeacherEntity> op = teacherRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		TeacherEntity teacher = op.get();

		// Get list of all users on page
		List<GradeDTO> retVal = null;
		if (classmaster) {
			Optional<GroupEntity> op1 = groupRepository.findByHomeClassMaster(teacher);
			if (op1.isPresent() == false) {
				loggingService.loggTwoOutMessage("Teacher is not class master", HttpStatus.BAD_REQUEST.toString(),
						Level.INFO);
				return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_TEACHER),
						HttpStatus.BAD_REQUEST);
			}
			List<GradeEntity> list = gradeService.getFiltered(null, null, null, op1.get().getId(), classId, stage);
			retVal = gradeService.createDTOList(list);
		} else {
			List<GradeEntity> list = gradeService.getFiltered(studentId, subjectId, teacher.getId(), groupId, classId,
					stage);
			retVal = gradeService.createDTOList(list);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}

}
