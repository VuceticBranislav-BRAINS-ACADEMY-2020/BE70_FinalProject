package com.iktakademija.FinalProject.controllers;

import java.util.List;
import java.util.Optional;

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
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewStudentDTO;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.StudentService;

/**
 * Student endpoint.
 * <BR>Provide all parent functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	// STU10
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllStudents() {
		return new ResponseEntity<List<StudentDTO>>(studentService.getDTOList(), HttpStatus.OK);
	}
	
	// STU11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(value = "id") Integer studentId) {
		if (studentId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		StudentDTO dto = studentService.getStudentDTO(studentId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);		
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);	
	}
	
	// STU12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setStudent(@PathVariable(value = "id") Integer studentId, @RequestBody NewStudentDTO newStudent) {
		if (studentId == null || newStudent == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		StudentDTO dto = studentService.setStudent(studentId, newStudent);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);	
	}
	
	// STU13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeStudent(@PathVariable(value = "id") Integer studentId) {
		if (studentId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		StudentDTO dto = studentService.removeStudent(studentId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<StudentDTO>(dto, HttpStatus.OK);	
	}
	
	/**
	 * Add new student to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/student/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>STU01</B>
	 * @return Added parent if there is no errors.
	 * @see ParentController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addStudent(@RequestBody NewStudentDTO newUser) {

		StudentEntity student = studentService.createStudent(newUser);
		if (student == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<StudentDTO>(studentService.createDTO(student), HttpStatus.OK);
	}
	
	// STU14
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/admin/getparents/{id}")
	public ResponseEntity<?> getAllParentsForChild(@PathVariable(value = "id") Integer studentID) {		
		Optional<StudentEntity> op = studentRepository.findById(studentID);
		if (op.isPresent() == false) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		StudentEntity students = op.get();		
		List<ParentDTO> parent = studentService.getAllParents(students);		
		return new ResponseEntity<List<ParentDTO>>(parent, HttpStatus.OK);			
	}
}
