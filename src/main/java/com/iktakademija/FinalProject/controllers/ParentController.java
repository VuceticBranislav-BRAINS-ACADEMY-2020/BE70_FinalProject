package com.iktakademija.FinalProject.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AdminService;
import com.iktakademija.FinalProject.services.ParentService;

/**
 * Parent endpoint.
 * <BR>Provide all parent functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/parent")
public class ParentController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Add new parent to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/parent/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>PAR01</B>
	 * @return Added parent if there is no errors.
	 * @see ParentController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addParent(@RequestBody NewUserDTO newUser) {

		ParentEntity user = adminService.createParent(newUser);
		user = parentRepository.save(user);
		return new ResponseEntity<ParentEntity>(user, HttpStatus.OK);

	}
	
//	@Secured("ROLE_ADMIN")
//	@JsonView(value = Views.Admin.class)
//	@RequestMapping(method = RequestMethod.PUT, path = "/admin/child/")
//	public ResponseEntity<?> addParent(@RequestParam("parent") Integer parentID, @RequestParam("child") Integer childID) {
//
//		if (parentID == null || childID == null) new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
//		
//		Optional<ParentEntity> opp = parentRepository.findById(parentID);
//		if (opp.isPresent() == false) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
//		ParentEntity parent = opp.get();
//		
//		Optional<StudentEntity> ops = studentRepository.findById(childID);
//		if (ops.isPresent() == false) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
//		StudentEntity student = ops.get();		
//		
//		parent.getStudent().add(new JoinTableStudentParent(parent, student));
//		
//		ParentEntity user = adminService.createParent(newUser);
//		user = parentRepository.save(user);
//		return new ResponseEntity<ParentEntity>(user, HttpStatus.OK);
//
//	}
	
}
