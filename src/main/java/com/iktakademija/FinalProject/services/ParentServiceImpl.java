package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PersonService personService;
	
	public List<ParentDTO> getDTOList(){
		
		List<ParentDTO> list = new ArrayList<>();
		
		for (ParentEntity parent : parentRepository.findAll()) {
			list.add(getDTOfromEntity(parent));
		}
		
		return list;
	};	
	
	public ParentDTO getParentDTO(Integer parentId){		
		
		Optional<ParentEntity> op = parentRepository.findById(parentId);
		if (op.isPresent() == false) return null;		
		
		return getDTOfromEntity(op.get());
	};	
	
	private ParentDTO getDTOfromEntity(ParentEntity source) {
		
		ParentDTO dto = new ParentDTO();
		
		if (source.getId() != null) {
			dto.setId(source.getId());	
		}
		
		if (source.getUsername() != null) {
			dto.setUsername(source.getUsername());	
		}
		
		if (source.getPersonality() != null) {
			dto.setPersonality(personService.createDTO(source.getPersonality()));
		}

		if (source.getRole() != null) {
			dto.setRole(roleService.createDTO(source.getRole()));
		}
		
		if (source.getStatus() != null) {
			dto.setStatus(source.getStatus());	
		}
		
		if (source.getEmail() != null) {
			dto.setEmail(source.getEmail());	
		}
		
		if (source.getId() != null) {
			List<Integer> asd = joinTableStudentParentRepository.findChildOfParent(source);
			dto.setChilds(asd);	
		}	
		
		return dto;
	}	
	
	@Override
	public ParentDTO createDTO(ParentEntity source) {
		
		ParentDTO retVal = new ParentDTO();
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPersonality(personService.createDTO(source.getPersonality()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setEmail(source.getEmail());
		retVal.setChilds(joinTableStudentParentRepository.findChildOfParent(source));
		return retVal;	
		
	}
	
}
