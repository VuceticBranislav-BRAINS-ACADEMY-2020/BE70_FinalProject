package com.iktakademija.FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private GroupService groupService;

	@Override
	public StudentDTO createDTO(StudentEntity source) {

		StudentDTO retVal = new StudentDTO();
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPersonality(personService.createDTO(source.getPersonality()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setClassgroup(groupService.createDTO(source.getClassgroup()));
		return retVal;

	}

}
