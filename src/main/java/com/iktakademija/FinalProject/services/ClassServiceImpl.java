package com.iktakademija.FinalProject.services;

import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.dtos.ClassDTO;

@Service
public class ClassServiceImpl implements ClassService {

	@Override
	public ClassDTO createDTO(ClassEntity source) {
		ClassDTO retVal = new ClassDTO();
		if (source == null) return retVal;
		
		retVal.setId(source.getId());
		retVal.setName(source.getName());
		retVal.setStatus(source.getStatus());
		retVal.setVersion(source.getVersion());
		retVal.setYear(source.getYear());
		return retVal;
	}
	
}
