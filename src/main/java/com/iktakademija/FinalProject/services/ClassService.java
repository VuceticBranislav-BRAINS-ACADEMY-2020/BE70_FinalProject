package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.dtos.ClassDTO;

public interface ClassService {

	ClassDTO createDTO(ClassEntity source);
	
}
