package com.iktakademija.FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Override
	public TeacherDTO createDTO(TeacherEntity source) {

		TeacherDTO retVal = new TeacherDTO();
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPersonality(personService.createDTO(source.getPersonality()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

}
