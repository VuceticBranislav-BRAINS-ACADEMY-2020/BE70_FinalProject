package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;

public interface StudentService {
	
	public StudentDTO createDTO(StudentEntity source);
	
}
