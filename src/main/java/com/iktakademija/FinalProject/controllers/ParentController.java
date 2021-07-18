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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
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
	
	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;
	
	// PAR02
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllParents() {		
		return new ResponseEntity<List<ParentDTO>>( parentService.getDTOList(), HttpStatus.OK);		
	}
	
	// PAR03
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getParentsById(@PathVariable(value = "id") Integer parentID) {		
		
		if (parentID == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		ParentDTO dto = parentService.getParentDTO(parentID);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		
		return new ResponseEntity<ParentDTO>(dto , HttpStatus.OK);		
	}
	
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
	
	// PAR04
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/child")
	public ResponseEntity<?> addChild(@RequestParam("parent") Integer parentID, @RequestParam("child") Integer childID) {

		if (parentID == null || childID == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		
		Optional<ParentEntity> opp = parentRepository.findById(parentID);
		if (opp.isPresent() == false) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		ParentEntity parent = opp.get();
		
		Optional<StudentEntity> ops = studentRepository.findById(childID);
		if (ops.isPresent() == false) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		StudentEntity student = ops.get();		
		
		
		JoinTableStudentParent join = new JoinTableStudentParent(parent, student);
		join = joinTableStudentParentRepository.save(join);

		return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);

	}
	
}
