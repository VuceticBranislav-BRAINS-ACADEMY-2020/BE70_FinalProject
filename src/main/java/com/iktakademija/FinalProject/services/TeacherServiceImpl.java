package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public TeacherEntity createTeacher(NewTeacherDTO source) {		
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_TEACHER);
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();
		
		TeacherEntity teacher = new TeacherEntity(source.getUsername(), Encryption.passwordEncode(source.getPassword()), person, role);		
		return teacherRepository.save(teacher);	
	}	
	
	@Override
	public TeacherDTO createDTO(TeacherEntity source) {
		TeacherDTO retVal = new TeacherDTO();
		if (source == null) return retVal;
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPerson(personService.createDTO(source.getPerson()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

	@Override
	public List<TeacherDTO> getDTOList() {
		List<TeacherDTO> list = new ArrayList<>();
		for (TeacherEntity teacher : teacherRepository.findAllUndeleted()) 
			list.add(this.createDTO(teacher));		
		return list;
	}

	@Override
	public TeacherDTO getTeacherDTO(Integer teacherId) {
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false) return null;
		return this.createDTO(op.get());
	}

	@Override
	public TeacherDTO setTeacher(Integer teacherId, NewTeacherDTO newTeacher) {
		Optional<TeacherEntity> opa = teacherRepository.findById(teacherId);
		if (opa.isPresent() == false) return null;
		TeacherEntity teacher = opa.get();		
		if (newTeacher.getPassword() != null) teacher.setPassword(Encryption.passwordEncode(newTeacher.getPassword()));
		
		Optional<PersonEntity> opp = personRepository.findById(newTeacher.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();	
		
		if (newTeacher.getPersonId() != null) teacher.setPerson(person); // TODO Fix this
		if (newTeacher.getUsername() != null) teacher.setUsername(newTeacher.getUsername());

		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);
	}

	@Override
	public TeacherDTO removeTeacher(Integer teacherId) {
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false) return null;
		TeacherEntity teacher = op.get();		
		teacher.setStatus(EStatus.DELETED);			
		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);	
	}

}
