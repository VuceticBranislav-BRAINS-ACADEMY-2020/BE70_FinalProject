package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.dtos.PersonDTO;

public interface PersonService {
	
	public PersonDTO createDTO(PersonEntity source);
	
}
