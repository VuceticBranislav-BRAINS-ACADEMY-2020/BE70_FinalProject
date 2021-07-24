package com.iktakademija.FinalProject.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.NewGradeDTO;
import com.iktakademija.FinalProject.exceptions.NewGradeDTOValidator;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.services.LoggingService;

@RestController
@RequestMapping(path = "/api/v1/grade")
public class GradeController {
	
	@Autowired
	private LoggingService loggingService;
	
	@Autowired
	private NewGradeDTOValidator newGradeDTOValidator;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;	
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(newGradeDTOValidator);
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("\n"));
	}	
	
	// GRD10
	@Secured(value = {"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.POST, path = "/add")
	public ResponseEntity<?> addGrade(@Valid @RequestBody NewGradeDTO newGrade, BindingResult result) {						
		
		if (result.hasErrors())
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		
		// Logging and retriving user role.
		loggingService.getRoleAndLogg(Level.INFO);	
		loggingService.loggMessage("Method: GradeController.addGrade()", Level.INFO);
		
		// Validation should do magic
		// Find teacher by ID. 
		TeacherEntity teacher = teacherRepository.findById(newGrade.getIdTeacher()).get();
		StudentEntity student = studentRepository.findById(newGrade.getIdStudent()).get();
		SubjectEntity subject = subjectRepository.findById(newGrade.getIdSubject()).get();
		GroupEntity group = groupRepository.findById(newGrade.getIdGroup()).get();
		
//		Optional<TeacherEntity> op1 = teacherRepository.findById(newGrade.getIdTeacher());
//		if (op1.isPresent() == false) new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.OK);
//		TeacherEntity teacher = op1.get();
		
		
		// Check is student in same goup a
		
		loggingService.loggMessage(newGrade.toString(), Level.INFO);
		
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<>(HttpStatus.OK);		

	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
//		return errors;
//		// samo jedan kluc ima jedan message
//		// za domaci da podrzi vise gresaka
//		// uraditi sa respons entity ubacivanjem Map<String, String>
//	}
//		// Primer koriscenja validatora direktno
//		userCustomValidator.validate(newUser, result);		
	
}
