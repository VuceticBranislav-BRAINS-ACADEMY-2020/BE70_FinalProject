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
import com.iktakademija.FinalProject.dtos.NewStudentDTO;
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
import com.iktakademija.FinalProject.services.StudentService;
import com.iktakademija.FinalProject.services.UserService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

/**
 * Student endpoint. <BR>
 * Provide all parent functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private UserService userService;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private ParentService parentService;

	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;

	@Autowired
	private GradeService gradeService;

	/**
	 * REST endpoint that returns all students from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>STU10</B>
	 * 
	 * @return set of {@link StudentDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllStudents() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: StudentController.getAllStudents()", Level.INFO);

		// Get list of all students
		List<StudentDTO> retVal = studentService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<StudentDTO>>(retVal, HttpStatus.OK);

	}

	/**
	 * REST endpoint that return students by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>STU11</B>
	 * 
	 * @return {@link StudentDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(value = "id") Integer studentId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AddressController.getAddressesById()", Level.INFO);

		if (studentId == null) {
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		StudentDTO dto = studentService.getStudentDTO(studentId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Student not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
	}

	// STU12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setStudent(@PathVariable(value = "id") Integer studentId,
			@RequestBody NewStudentDTO newStudent) {
		if (studentId == null || newStudent == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		StudentDTO dto = studentService.setStudent(studentId, newStudent);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
	}

	// STU13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeStudent(@PathVariable(value = "id") Integer studentId) {
		if (studentId == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		StudentDTO dto = studentService.removeStudent(studentId);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new student to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/student/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>STU01</B>
	 * 
	 * @return Added parent if there is no errors.
	 * @see ParentController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addStudent(@Valid @RequestBody NewStudentDTO newUser) {

		StudentEntity student = studentService.createStudent(newUser);
		if (student == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		return new ResponseEntity<StudentDTO>(studentService.createDTO(student), HttpStatus.OK);
	}

	// STU14
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/admin/getparents/{id}")
	public ResponseEntity<?> getAllParentsForChild(@PathVariable(value = "id") Integer studentID) {
		Optional<StudentEntity> op = studentRepository.findById(studentID);
		if (op.isPresent() == false)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		StudentEntity students = op.get();
		List<ParentDTO> parent = studentService.getAllParents(students);
		return new ResponseEntity<List<ParentDTO>>(parent, HttpStatus.OK);
	}

	/**
	 * Student preview general informations.<BR>
	 * Postman code: <B>STU20</B>
	 */
	@Secured("ROLE_STUDENT")
	@JsonView(value = Views.Student.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public ResponseEntity<?> getStudentInfo() {

		// Logging and retriving user informations
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: StudentController.getStudentInfo", Level.INFO);

		// Find out is student valid
		boolean isValid = false;
		StudentEntity student = null;
		if (user instanceof StudentEntity) {
			student = (StudentEntity) user;
			if (student.getStatus().equals(EStatus.ACTIVE)) {
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
		StudentDTO dto = studentService.createDTO(student);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);
	}

	// PAR15
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/parent")
	public ResponseEntity<?> addParent(@RequestParam("child") Integer childID,
			@RequestParam("parent") Integer parentID) {
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

		List<ParentEntity> list = parentRepository.findAllParents(student);

		return new ResponseEntity<List<ParentDTO>>(parentService.createDTOList(list), HttpStatus.OK);
	}

	/**
	 * Change password.<BR>
	 * Postman code: <B>STU30</B>
	 */
	@Secured("ROLE_STUDENT")
	@JsonView(value = Views.Student.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/changecredentials")
	public ResponseEntity<?> changeUsernameAndPassword(@RequestParam("user") String newUsername,
			@RequestParam("pass") String newPassword) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: StudentController.changeUsernamAndPassword()", Level.INFO);

		// Check id
		Optional<StudentEntity> op = studentRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		StudentEntity student = op.get();

		// Check is password and username successful
		if (userService.changeUsernameAndPasswor(student, newUsername, newPassword) == false) {
			loggingService.loggTwoOutMessage("Password or username can not be changed.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<StudentDTO>(studentService.createDTO(student), HttpStatus.OK);
	}

	/**
	 * Filter all grades
	 * 
	 * Postman code: <B>STU40</B>
	 */
	@Secured("ROLE_STUDENT")
	@JsonView(value = Views.Student.class)
	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public ResponseEntity<?> searchAll(@RequestParam(required = false, name = "SubjectId") Integer subjectId,
			@RequestParam(required = false, name = "TeacherId") Integer teacherId,
			@RequestParam(required = false, name = "GroupId") Integer groupId,
			@RequestParam(required = false, name = "ClassId") Integer classId,
			@RequestParam(required = false, name = "Stage") EStage stage) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: StudentController.searchAll()", Level.INFO);

		// Check id
		Optional<StudentEntity> op = studentRepository.findById(user.getId());
		if (op.isPresent() == false) {
			loggingService.loggTwoOutMessage("Invalid id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}
		StudentEntity student = op.get();

		// Get list of all users on page
		List<GradeEntity> list = gradeService.getFiltered(student.getId(), subjectId, teacherId, groupId, classId,
				stage);
		List<GradeDTO> retVal = gradeService.createDTOList(list);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}

}
