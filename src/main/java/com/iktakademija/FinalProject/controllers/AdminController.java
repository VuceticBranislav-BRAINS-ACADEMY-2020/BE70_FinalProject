package com.iktakademija.FinalProject.controllers;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.dtos.AdminDTO;
import com.iktakademija.FinalProject.dtos.EmailObject;
import com.iktakademija.FinalProject.dtos.NewAdminDTO;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AdminService;
import com.iktakademija.FinalProject.services.EmailService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

/**
 * Admin endpoint. <BR>
 * Provide all admin functionalities.
 * 
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

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private EmailService emailService;

	/**
	 * Returns all admins from data base. If there is no internal error method
	 * return {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * REST method: <B>GET</B><BR>
	 * Path inside controller: <B>"/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM10</B>
	 * 
	 * @return List of {@link AdminEntity} from database or empty list if nothing to
	 *         return.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "")
	public ResponseEntity<?> getAllAdmins() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AdminController.getAllAdmins()", Level.INFO);

		// Get list of all admins
		List<AdminDTO> retVal = adminService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<AdminDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * REST endpoint that returns admins from data base by id.
	 * 
	 * Postman code: <B>ADM11</B>
	 * 
	 * @return {@link AdminDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> getAdminById(@PathVariable(value = "id") Integer adminId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AdminController.getAdminById()", Level.INFO);

		if (adminId == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		AdminDTO dto = adminService.getAdminDTO(adminId);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing admin entity
	 * 
	 * Postman code: <B>ADM12</B>
	 * 
	 * @return changed admin entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> setAdmin(@PathVariable(value = "id") Integer adminId, @RequestBody NewAdminDTO newAdmin) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AdminController.setAdmin()", Level.INFO);

		if (adminId == null || newAdmin == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		AdminDTO dto = adminService.setAdmin(adminId, newAdmin);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for removing admin entity.
	 * 
	 * Postman code: <B>ADM13</B>
	 * 
	 * @return removed admin entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<?> removeAdresses(@PathVariable(value = "id") Integer adminId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AdminController.removeAdresses()", Level.INFO);

		if (adminId == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		AdminDTO dto = adminService.removeAdmin(adminId);
		if (dto == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new user to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * <BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/admin/user"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>ADM04</B>
	 * 
	 * @return Added administrator if there is no errors.
	 * @see AdminController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "")
	public ResponseEntity<?> addAdmin(@Valid @RequestBody NewAdminDTO newUser) {

		AdminEntity user = adminService.createAdmin(newUser);
		if (user == null)
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AdminDTO>(adminService.createDTO(user), HttpStatus.OK);

	}

	/**
	 * Send log file to administrator
	 * 
	 * Postman code: <B>ADM02</B>
	 * 
	 * @return removed teacher entity.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/sendlogfile")
	public ResponseEntity<?> sendLogFile() {

		// Logging and retriving user informations
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: AdminController.sendLogFile", Level.INFO);

		// Find out is admin valid
		boolean isValid = false;
		AdminEntity admin = null;
		if (user instanceof AdminEntity) {
			admin = (AdminEntity) user;
			if (admin.getStatus().equals(EStatus.ACTIVE))
				isValid = true;
		}

		// Logg validation status of student and exit if invalid
		if (isValid) {
			loggingService.loggMessage("Authorized to send log file.", Level.INFO);
		} else {
			loggingService.loggMessage("NOT Authorized to send log file", Level.WARN);
			loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.ACCESS_NOT_ALLOWED),
					HttpStatus.BAD_REQUEST);
		}

		// Send mail with attachment
		EmailObject object = new EmailObject();
		object.setTo(emailService.emailDeture(admin.getEmail()));
		object.setSubject("Log file " + LocalDate.now());

		try {
			emailService.sendMessageWithAttachment(object, "logs/logging.log");
			loggingService.loggMessage("Grade added successfully.", Level.INFO);
		} catch (Exception e) {
			loggingService.loggMessage("Grade adding fail.", Level.ERROR);
			loggingService.loggOutMessage(HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.SOMETHING_WRONG),
					HttpStatus.BAD_REQUEST);
		}
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<String>("Log file sent.", HttpStatus.OK);
	}

}
