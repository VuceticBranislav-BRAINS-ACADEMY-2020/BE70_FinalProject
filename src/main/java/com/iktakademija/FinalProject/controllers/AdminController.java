package com.iktakademija.FinalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewPersonDTO;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.repositories.AdminRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AdminService;

/**
 * Admin endpoint.
 * <BR>Provide all admin functionalities
 * @see #getAllAdmins 
 * @see #addAdmin
 * @see #addAddress
 * @see #addPerson
 */
@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private StudentRepository studentRepository;	
//	
//	@Autowired
//	private ParentRepository parentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	@Autowired
//	private TeacherRepository teacherRepository;
	
	/**
	 * Returns all admins from data base. 
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>GET</B><BR>
	 * Path inside controller: <B>"/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM01</B>
	 * @return List of {@link AdminEntity} from database or empty list if nothing to return.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllAdmins() {

		return new ResponseEntity<List<AdminDTO>>((List<AdminDTO>) adminService.findAllAdmins(), HttpStatus.OK);	
		
	}
	
	/**
	 * Add new user to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin/user"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM04</B>
	 * @return Added administrator if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "")
	public ResponseEntity<?> addAdmin(@RequestBody NewUserDTO newUser) {	
		
		AdminEntity user = adminService.createAdmin(newUser);
		user = adminRepository.save(user);
		return new ResponseEntity<AdminEntity>(user, HttpStatus.OK);
	
	}
	
	/**
	 * Add new adress to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin/address"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM02</B>
	 * @return Added adress if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/address")
	public ResponseEntity<?> addAddress(@RequestBody NewAddressDTO newAddress) {	
		
		AddressEntity address = adminService.createAddress(newAddress);	
		if (address == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AddressEntity>(address, HttpStatus.OK);
	}
	
	/**
	 * Add new person to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin/person"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM03</B>
	 * @return Added person if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/person")
	public ResponseEntity<?> addPerson(@RequestBody NewPersonDTO newPerson) {	
		
		PersonEntity person = adminService.createPerson(newPerson);	
		if (person == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<PersonEntity>(person, HttpStatus.OK);
	}
}
