package com.iktakademija.FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.AdminService;

/**
 * Teacher endpoint.
 * <BR>Provide all teacher functionalities.
 */
@RestController
@RequestMapping(path = "/api/v1/teacher")
public class TeacherController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	/**
	 * Add new teacher to database.
	 * If there is no internal error method return {@link HttpStatus.OK}.<BR><BR>
	 * 
	 * REST method: <B>POST</B><BR>
	 * Path inside controller: <B>"/teacher/admin"</B><BR>
	 * Allowed for <B>Admin</B><BR>
	 * Error status messages: <B>none</B><BR>
	 * Postman identification tag: <B>TEA01</B>
	 * @return Added teacher if there is no errors.
	 * @see TeacherController
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.POST, path = "/admin")
	public ResponseEntity<?> addTeacher(@RequestBody NewUserDTO newUser) {

		TeacherEntity user = adminService.createTeacher(newUser);
		user = teacherRepository.save(user);
		return new ResponseEntity<TeacherEntity>(user, HttpStatus.OK);

	}
	
}
