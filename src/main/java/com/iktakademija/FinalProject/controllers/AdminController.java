package com.iktakademija.FinalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.repositories.AdminRepository;

/**
 * Endpoint controller for admins.
 * @see AdminController#getAllAdmins
 */
@RestController
@RequestMapping(path = "/api/v1")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
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
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAdmins() {

		// return new ResponseEntity<RESTMessage>(new RESTMessage(ERESTMessageCodes.LIST_EMPTY), HttpStatus.OK);
		return new ResponseEntity<List<AdminEntity>>((List<AdminEntity>) adminRepository.findAll(), HttpStatus.OK);		
	}

}
