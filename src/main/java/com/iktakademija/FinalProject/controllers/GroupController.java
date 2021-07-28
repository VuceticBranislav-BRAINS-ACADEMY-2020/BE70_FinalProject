package com.iktakademija.FinalProject.controllers;

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
import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.dtos.GroupDTO;
import com.iktakademija.FinalProject.entities.dtos.NewGroupDTO;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.GroupService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;

@RestController
@RequestMapping(path = "/api/v1/group")
public class GroupController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private GroupService groupService;

	/**
	 * REST endpoint that returns all groups from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>GRP10</B>
	 * 
	 * @return set of {@link GroupDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllGroups() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GroupController.getAllGroups()", Level.INFO);

		// Get list of all users
		List<GroupDTO> retVal = groupService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GroupDTO>>(retVal, HttpStatus.OK);
	}

	/**
	 * REST endpoint that returns group from data base by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>GRP11</B>
	 * 
	 * @return {@link GroupDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable(value = "id") Integer groupId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GroupController.getGroupById()", Level.INFO);

		// Check teacher id
		if (groupId == null) {
			loggingService.loggTwoOutMessage("Invalid group id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		GroupDTO dto = groupService.getGroupDTO(groupId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Group not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);
	}

	/**
	 * Add new group to database. If there is no internal error method return
	 * {@link HttpStatus.OK}.<BR>
	 * 
	 * Postman code: <B>GRP01</B>
	 * 
	 * @return Added group if there is no errors.
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addGroup(@Valid @RequestBody NewGroupDTO newGroup) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GroupController.addTeacher()", Level.INFO);

		GroupEntity group = groupService.createGroup(newGroup);
		if (group == null) {
			loggingService.loggTwoOutMessage("Group can not be added. Invalid data provided.",
					HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<GroupDTO>(groupService.createDTO(group), HttpStatus.OK);
	}

	/**
	 * REST endpoint for changing group entity
	 * 
	 * Postman code: <B>GRP12</B>
	 * 
	 * @return changed group entity DTO or error code.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/{id}")
	public ResponseEntity<?> setGroup(@PathVariable(value = "id") Integer groupId, @RequestBody NewGroupDTO newGroup) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GroupController.setGroup()", Level.INFO);

		if (groupId == null || newGroup == null) {
			loggingService.loggTwoOutMessage("Invalid group id or new group data.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		// Change existing group
		GroupDTO dto = groupService.setGroup(groupId, newGroup);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);
	}

	/**
	 * REST endpoint for removing group entity.
	 * 
	 * Postman code: <B>GRP13</B>
	 * 
	 * @return removed group entity.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, path = "/admin/{id}")
	public ResponseEntity<?> removeGroup(@PathVariable(value = "id") Integer groupId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GroupController.removeGroup()", Level.INFO);

		// Find teacher by id
		if (groupId == null) {
			loggingService.loggTwoOutMessage("Invalid teacher id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		GroupDTO dto = groupService.removeGroup(groupId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Group not found. Nothing changed.", HttpStatus.BAD_REQUEST.toString(),
					Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);
	}

}
