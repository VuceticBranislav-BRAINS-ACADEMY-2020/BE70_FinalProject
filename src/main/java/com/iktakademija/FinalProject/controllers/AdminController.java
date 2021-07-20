package com.iktakademija.FinalProject.controllers;

import java.util.List;

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
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAdminDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AdminService;

/**
 * Admin endpoint.
 * <BR>Provide all admin functionalities.
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
	
	/**
	 * Returns all admins from data base. 
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>GET</B><BR>
	 * Path inside controller: <B>"/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM10</B>
	 * @return List of {@link AdminEntity} from database or empty list if nothing to return.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllAdmins() {
		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = principal.toString();			
//		return new ResponseEntity<String>("Logged as : " + username, HttpStatus.OK);
		
		return new ResponseEntity<List<AdminDTO>>(adminService.getDTOList(), HttpStatus.OK);
	}
	
	// ADM11
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getAdminById(@PathVariable(value = "id") Integer adminId) {
		if (adminId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);		
		AdminDTO dto = adminService.getAdminDTO(adminId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);		
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);	
	}
	
	// ADM12
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> setAdmin(@PathVariable(value = "id") Integer adminId, @RequestBody NewAdminDTO newAdmin) {
		if (adminId == null || newAdmin == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		AdminDTO dto = adminService.setAdmin(adminId, newAdmin);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);	
	}
	
	// ADR13
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<?> removeAdresses(@PathVariable(value = "id") Integer adminId) {
		if (adminId == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);			
		AdminDTO dto = adminService.removeAdmin(adminId);
		if (dto == null) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);	
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
	public ResponseEntity<?> addAdmin(@RequestBody NewAdminDTO newUser) {	
		
		AdminEntity user = adminService.createAdmin(newUser);
		if (user == null ) return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<AdminDTO>(adminService.createDTO(user), HttpStatus.OK);
	
	}
	
}
